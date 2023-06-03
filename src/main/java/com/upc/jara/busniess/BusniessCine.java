package com.upc.jara.busniess;

import com.upc.jara.dto.ClientCineDTO;
import com.upc.jara.entity.ClientCine;
import com.upc.jara.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusniessCine {
    @Autowired
    private Repository repository;
    public ClientCine register(ClientCine clientCine){return repository.save(clientCine);}

    public List<ClientCineDTO> convertToLisDto(List<ClientCine> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private ClientCineDTO convertToDto(ClientCine clientCine) {
        ModelMapper modelMapper = new ModelMapper();
        ClientCineDTO clientCineDTO = modelMapper.map(clientCine, ClientCineDTO.class);
        return clientCineDTO;
    }

    public double cuotaNeta(ClientCineDTO clientCineDTO){
        return (40/1+ clientCineDTO.getJlStock()*2000);
    }
    public double calcularResultadoFinal(ClientCineDTO clientCineDTO) {
        double result = cuotaNeta(clientCineDTO);
        if (result > 3) {

            return  1.1*result;
        } else {
            return  result;
        }
    }
    public List<ClientCine> obtenerReporte(){
        return repository.findAll();
    }


    public List<ClientCineDTO> obtenerReporteResultados(){
        List<ClientCine> cine;
        cine = obtenerReporte();
        List<ClientCineDTO> clientCineDTOS;
        clientCineDTOS = convertToLisDto(cine);

        for(ClientCineDTO p:clientCineDTOS){
            p.setJlCuotaNeta(cuotaNeta(p));
        }
        return clientCineDTOS;
    }

    public ClientCine actualizarClient(ClientCine clientCine) throws Exception{
        repository.findById(clientCine.getJlId()).orElseThrow(()-> new Exception("No se encontró entidad"));
        return repository.save(clientCine);
    }

    public double obtenerClient (Long dni){
        ClientCine clientCine = repository.findByJlDni(dni);
       ClientCineDTO clientCineDTO = convertToDto(clientCine);
        return calcularResultadoFinal(clientCineDTO);
    }




}
