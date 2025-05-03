package com.luisnomosquera.snaplabs.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luisnomosquera.snaplabs.dto.response.ApiResponseDto;
import com.luisnomosquera.snaplabs.dto.response.DetallesCartaResponseDto;
import com.luisnomosquera.snaplabs.dto.response.SimpleCartaResponseDto;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MarvelSnapApiService {

    Dotenv dotenv = Dotenv.load();
    private final String API_KEY = dotenv.get("MARVEL_SNAP_API_KEY");
    private final String API_HOST = dotenv.get("MARVEL_SNAP_API_HOST");
    private final String API_URL_ALL = dotenv.get("MARVEL_SNAP_API_URL_ALL_CARDS");
    private final String API_KEY_DETAILS = dotenv.get("MARVEL_SNAP_API_URL_CARDS_DETAILS");

    public List<SimpleCartaResponseDto> getArrayCartas() {
        Integer pagina = 1;
        boolean continuar = true;
        ObjectMapper mapper = new ObjectMapper();
        List<SimpleCartaResponseDto> listaCartas = new ArrayList<>();

        try {
            while (continuar) {;
                String respuesta = peticionApi(API_URL_ALL, pagina.toString());
                ApiResponseDto apiResponseDto = mapper.readValue(respuesta, ApiResponseDto.class);
                Integer next = apiResponseDto.getNext();
                List<SimpleCartaResponseDto> listaCartasPagina = addDatos(apiResponseDto.getData());

                listaCartas.addAll(listaCartasPagina);

                if (next != null)
                    pagina = next;
                else {
                    continuar = false;
                }
            }
        } catch (Exception e) {
            throw  new RuntimeException(e.getMessage());
        }
        return listaCartas;
    }

    public DetallesCartaResponseDto getDetallesCarta(String clave) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String detalles = peticionApi(API_KEY_DETAILS, clave);
            DetallesCartaResponseDto[] carta = mapper.readValue(detalles, DetallesCartaResponseDto[].class);
            return carta[0];
        } catch (Exception e) {
            throw  new RuntimeException(e.getMessage());
        }
    }

    private List<SimpleCartaResponseDto> addDatos(List<SimpleCartaResponseDto> lista) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            for (SimpleCartaResponseDto carta: lista) {
                String detalles = peticionApi(API_KEY_DETAILS, carta.getClave());
                JsonNode jsonNode = mapper.readTree(detalles);
                String serie = jsonNode.get(0).get("series_key").asText();
                int coste = jsonNode.get(0).get("cost").asInt();
                int poder = jsonNode.get(0).get("power").asInt();
                List<String> habilidades = jsonNode.get(0).get("card_abilities").isEmpty() ?
                        Arrays.asList("No Ability") : filtrarHabilidades(jsonNode);
                carta.setSerie(serie);
                carta.setCoste(coste);
                carta.setPoder(poder);
                carta.setHabilidades(String.join(",", habilidades));
            }
        } catch (Exception e) {
            throw  new RuntimeException(e.getMessage());
        }

        return lista;
    }

    private String peticionApi(String url, String parametro) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url + parametro)
                .get()
                .addHeader("x-rapidapi-key", API_KEY)
                .addHeader("x-rapidapi-host", API_HOST)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful())
                return response.body().string();
            else
                throw new RuntimeException("HttpResponseCode: " + response.code());
        } catch (IOException e) {
            throw new RuntimeException("Error al realizar la peticion a la API de Marvel Snap");
        }
    }

    private List<String> filtrarHabilidades(JsonNode jsonNode) {
        List<String> habilidades = new ArrayList<>();
        for (JsonNode nodo: jsonNode.get(0).get("card_abilities")) {
            String habilidad = nodo.get("name").asText();
            switch (habilidad) {
                case "Destroy":
                case "Discard":
                case "Move":
                case "No Ability":
                case "On Reveal":
                case "Ongoing": habilidades.add(habilidad); break;
                default: break;
            }
        }
        if (habilidades.isEmpty()) habilidades.add(null);
        return habilidades;
    }
}
