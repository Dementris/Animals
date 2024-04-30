package com.dementris.testtask.controller;

import com.dementris.testtask.ErrorHandling;
import com.dementris.testtask.exceptions.ErrorResponse;
import com.dementris.testtask.model.Animal;
import com.dementris.testtask.model.AnimalsOrchestrator;
import com.dementris.testtask.service.Mapper;
import com.dementris.testtask.service.dto.AnimalDto;
import com.dementris.testtask.service.dto.AnimalsParamsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;


/**
 * Animal Controller
 */
@RestController
@RequestMapping("/api/v1")
public class AnimalController {

    private final AnimalsOrchestrator animalsOrchestrator;
    private final Mapper mapper;

    @Autowired
    public AnimalController(AnimalsOrchestrator animalsOrchestrator, Mapper mapper) {
        this.animalsOrchestrator = animalsOrchestrator;
        this.mapper = mapper;
    }

    /**
     * Method get() send GET request.
     * Retrieves data from database with filters params and ordered by.
     *
     * @param type Animal type
     * @param category Animal category from 1 to 4
     * @param sex Animal sex "male" or "female"
     * @param orderBy Order by "id", "name", "type", "category", "sex", "weight", "cost"
     * @return response
     */
    @Operation(
            description = "GET request retrieves data from database with filters params and ordered by",
            summary = "Get animals",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Animal Not found by these parameters ",
                            responseCode = "404",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<AnimalDto>> get(
            @RequestParam String type,
            @RequestParam Integer category,
            @RequestParam String sex,
            @RequestParam(defaultValue = "id") String orderBy
            ){
        AnimalsParamsDto paramsDto = new AnimalsParamsDto(
                type,
                category,
                sex,
                orderBy);
        List<Animal> entities = animalsOrchestrator.get(paramsDto);
        return ResponseEntity.ok(entities.stream().map(mapper::animalToDto).toList());
    }

    /**
     * Method post() send POST request.
     * Add data from files to the database and returns a response with added objects.
     *
     * @param file File with content type text/csv or text/xml
     * @return response
     */
    @Operation(
            description = "Post request. Add data from files to the database and returns a response with added objects.",
            summary = "Upload Animals from file",
            responses = {
                    @ApiResponse(
                            description = "Animal successfully added to DB",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Invalid file content type or data ",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    @PostMapping(value="file/upload", consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AnimalDto>> post(@RequestPart(value = "file") MultipartFile file){
        List<Animal> animals = animalsOrchestrator.post(file);
        return ResponseEntity.ok(animals.stream().map(mapper::animalToDto).toList());
    }

}
