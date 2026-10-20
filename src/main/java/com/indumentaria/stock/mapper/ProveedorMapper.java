package com.indumentaria.stock.mapper;

import com.indumentaria.stock.dto.ProveedorRequestDTO;
import com.indumentaria.stock.dto.ProveedorResponseDTO;
import com.indumentaria.stock.entity.Proveedor;
import org.springframework.stereotype.Component;

@Component
public class ProveedorMapper {

    public ProveedorResponseDTO entityToDTO(Proveedor proveedor) {
        return new ProveedorResponseDTO(
                proveedor.getId(),
                proveedor.getNombreContacto(),
                proveedor.getNombreEmpresa(),
                proveedor.getTelefono()
        );
    }

    public Proveedor dtoToEntity(ProveedorRequestDTO dto) {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombreContacto(dto.nombreContacto());
        proveedor.setNombreEmpresa(dto.nombreEmpresa());
        proveedor.setTelefono(dto.telefono());
        return proveedor;
    }
}