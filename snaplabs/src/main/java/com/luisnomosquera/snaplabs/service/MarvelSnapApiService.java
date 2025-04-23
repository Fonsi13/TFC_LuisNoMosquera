package com.luisnomosquera.snaplabs.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luisnomosquera.snaplabs.dto.response.ApiResponseDto;
import com.luisnomosquera.snaplabs.dto.response.CartaResponseDto;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarvelSnapApiService {

    private final String API_KEY = Dotenv.load().get("MARVEL_SNAP_API_KEY");

    public List<CartaResponseDto> getArrayCartas() {
        int pagina = 1;
        boolean continuar = true;
        ObjectMapper objectMapper = new ObjectMapper();
        List<CartaResponseDto> listaCartas = new ArrayList<>();

        try {
            while (continuar) {;
                String respuesta = getAllCards(pagina);
                ApiResponseDto apiResponseDto = objectMapper.readValue(respuesta, ApiResponseDto.class);
                Integer next = apiResponseDto.getNext();
                List<CartaResponseDto> listaCartasPagina = addSeries(apiResponseDto.getData());

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

    private List<CartaResponseDto> addSeries(List<CartaResponseDto> lista) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            for (CartaResponseDto carta: lista) {
                String detalles = getDetalles(carta.getClave());
                JsonNode jsonNode = mapper.readTree(detalles);
                String series = jsonNode.get(0).get("series_key").asText();
                carta.setSeries(series);
            }
        } catch (Exception e) {
            throw  new RuntimeException(e.getMessage());
        }

        return lista;
    }

    private String getAllCards(int pagina) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://marvel-snap-api.p.rapidapi.com/api/get-all-cards?page=" + pagina)
                .get()
                .addHeader("x-rapidapi-key", API_KEY)
                .addHeader("x-rapidapi-host", "marvel-snap-api.p.rapidapi.com")
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

    private String getDetalles(String clave) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://marvel-snap-api.p.rapidapi.com/api/get-card-details/" + clave)
                .get()
                .addHeader("x-rapidapi-key", API_KEY)
                .addHeader("x-rapidapi-host", "marvel-snap-api.p.rapidapi.com")
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
}
