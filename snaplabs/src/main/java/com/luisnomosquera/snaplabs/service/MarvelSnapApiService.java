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

    Dotenv dotenv = Dotenv.load();
    private final String API_KEY = dotenv.get("MARVEL_SNAP_API_KEY");
    private final String API_HOST = dotenv.get("MARVEL_SNAP_API_HOST");
    private final String API_URL_ALL = dotenv.get("MARVEL_SNAP_API_URL_ALL_CARDS");
    private final String API_KEY_DETAILS = dotenv.get("MARVEL_SNAP_API_URL_CARDS_DETAILS");

    public List<CartaResponseDto> getArrayCartas() {
        Integer pagina = 1;
        boolean continuar = true;
        ObjectMapper objectMapper = new ObjectMapper();
        List<CartaResponseDto> listaCartas = new ArrayList<>();

        try {
            while (continuar) {;
                String respuesta = peticionApi(API_URL_ALL, pagina.toString());
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
                String detalles = peticionApi(API_KEY_DETAILS, carta.getClave());
                JsonNode jsonNode = mapper.readTree(detalles);
                String series = jsonNode.get(0).get("series_key").asText();
                carta.setSeries(series);
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
}
