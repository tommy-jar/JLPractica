package com.upc.jara.controller;

import com.upc.jara.busniess.BusniessCine;
import com.upc.jara.dto.ClientCineDTO;
import com.upc.jara.entity.ClientCine;
import org.slf4j.LoggerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")

public class ControllerCine {
    @Autowired
    private BusniessCine busniessCine;
    Logger logger = LoggerFactory.getLogger(ClientCineDTO.class);
    @PostMapping("/client")
    public ResponseEntity<ClientCineDTO> register(@RequestBody ClientCineDTO clientCineDTO){
       ClientCine clientCine = convertToEntity(clientCineDTO);
        clientCine = busniessCine.register(clientCine);
      clientCineDTO = convertToDto(clientCine);
        return  new ResponseEntity<ClientCineDTO>(clientCineDTO, HttpStatus.OK);
    }
    private ClientCineDTO convertToDto(ClientCine clientCine) {
        ModelMapper modelMapper = new ModelMapper();
        ClientCineDTO clientCineDTO = modelMapper.map(clientCine, ClientCineDTO.class);
        return clientCineDTO;
    }

    private ClientCine convertToEntity(ClientCineDTO clientCineDTO) {
        ModelMapper modelMapper = new ModelMapper();
        ClientCine post = modelMapper.map(clientCineDTO, ClientCine.class);
        return post;
    }

    private List<ClientCineDTO> convertToLisDto(List<ClientCine> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    /*
    @GetMapping("/clients")
    public ResponseEntity<List<ClientCineDTO>> obtenerClientes(){
        List<ClientCine> list = busniessCine.obtenerReporte();
        List<ClientCineDTO> listDTO = convertToLisDto(list);
        return  new ResponseEntity<List<ClientCineDTO>>(listDTO, HttpStatus.OK);
    }*/

    @GetMapping("/clientsCineResult") //3.-
    public List<ClientCineDTO> obtenerReporteResultados(){
        List<ClientCineDTO> centroSaludDTOS;
        try {
            centroSaludDTOS = busniessCine.obtenerReporteResultados();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No fue posible obtener listado");
        }
        return centroSaludDTOS;
    }
    @PutMapping("/clientActualizar")
    public ResponseEntity<ClientCineDTO> actualizarTuristas(@RequestBody ClientCineDTO clientDetalle) {
        ClientCineDTO touristDTO;
        ClientCine clientCine;
        try {
            clientCine = convertToEntity(clientDetalle);
            logger.debug("Actualizando lista");
            clientCine = busniessCine.actualizarClient(clientCine);
            logger.debug("Turista Actualizado");
            touristDTO = convertToDto(clientCine);
            return new ResponseEntity<ClientCineDTO>(touristDTO, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error de Actualización", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no se pudo actualizar, sorry");
        }
    }

    @GetMapping ("/clientsCine/{xDni}")
    public ResponseEntity<Double> obtenerReportelist (@PathVariable(value = "xDni") Long xDni)
            throws Exception
    {
        Double evaluado;
        try {
            evaluado = busniessCine.obtenerClient(xDni);
        } catch(Exception e){

            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No fue posible encontrar su registro");
        }

        return new ResponseEntity<Double>(evaluado, HttpStatus.OK);
    }

}
