package com.prueba.franquicia.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.franquicia.entity.Franquicia;
import com.prueba.franquicia.entity.Producto;
import com.prueba.franquicia.entity.Sucursal;
import com.prueba.franquicia.repository.FranquiciaRepository;
import com.prueba.franquicia.repository.ProductoRepository;
import com.prueba.franquicia.repository.SucursalRepository;

@Service
public class FranquiciaService {
	
	@Autowired
    private FranquiciaRepository franquiciaRepository;
	
	@Autowired
    private SucursalRepository sucursalRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	public Franquicia agregarFranquicia(Franquicia franquicia) {
        // Validaciones básicas, si se necesita
        if (franquicia.getNombre() == null || franquicia.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la franquicia no puede estar vacío.");
        }

        // Guardar franquicia en la base de datos
        return franquiciaRepository.save(franquicia);
    }
	
	public Sucursal agregarSucursal(Long franquiciaId, Sucursal sucursal) {
        // Buscar la franquicia por ID
        Optional<Franquicia> franquiciaOpt = franquiciaRepository.findById(franquiciaId);

        if (franquiciaOpt.isEmpty()) {
            throw new IllegalArgumentException("La franquicia con ID " + franquiciaId + " no existe.");
        }

        // Obtener la franquicia encontrada
        Franquicia franquicia = franquiciaOpt.get();

        // Asignar la franquicia a la sucursal
        sucursal.setFranquicia(franquicia);

        // Guardar la sucursal en la base de datos
        return sucursalRepository.save(sucursal);

    }
	
	public Producto agregarProducto(Long sucursalId, Producto producto) {
        // Buscar la franquicia por ID
        Optional<Sucursal> sucursalOpt = sucursalRepository.findById(sucursalId);

        if (sucursalOpt.isEmpty()) {
            throw new IllegalArgumentException("La sucursal con ID " + sucursalId + " no existe.");
        }

        // Obtener la franquicia encontrada
        Sucursal sucursal = sucursalOpt.get();

        // Asignar la franquicia a la sucursal
        producto.setSucursal(sucursal);

        // Guardar la sucursal en la base de datos
        return productoRepository.save(producto);

    }
	
	public void eliminarProducto(Long productoId) {
        // Verificar si el producto existe
        if (!productoRepository.existsById(productoId)) {
            throw new IllegalArgumentException("El producto con ID " + productoId + " no existe.");
        }

        // Eliminar el producto
        productoRepository.deleteById(productoId);
    }
	
	public Producto modificarStock(Long productoId, Integer nuevoStock) {
        // Buscar el producto por su ID
        Optional<Producto> productoOpt = productoRepository.findById(productoId);

        if (productoOpt.isEmpty()) {
            throw new IllegalArgumentException("El producto con ID " + productoId + " no existe.");
        }

        // Obtener el producto y actualizar el stock
        Producto producto = productoOpt.get();
        if (nuevoStock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        producto.setStock(nuevoStock);

        // Guardar el producto actualizado en la base de datos
        return productoRepository.save(producto);
    }
	
	public List<Producto> obtenerProductoConMasStockPorSucursal(Long franquiciaId) {

        List<Sucursal> sucursales = sucursalRepository.findByFranquiciaId(franquiciaId);

        return sucursales.stream().map(sucursal -> {

            List<Producto> productos = productoRepository.findBySucursal(sucursal);

            Producto productoConMasStock = productos.stream()
                .max((p1, p2) -> Integer.compare(p1.getStock(), p2.getStock()))
                .orElse(null);

            return productoConMasStock;
        }).collect(Collectors.toList());
    }
	
	public Franquicia modificarNombreFranquicia(Long franquiciaId, String nombre) {
		
        Optional<Franquicia> franquiciaOpt = franquiciaRepository.findById(franquiciaId);

        if (franquiciaOpt.isEmpty()) {
            throw new IllegalArgumentException("La franquicia con ID " + franquiciaId + " no existe.");
        }

        Franquicia franquicia = franquiciaOpt.get();
       
        franquicia.setNombre(nombre);

        return franquiciaRepository.save(franquicia);
    }
	
	public Sucursal modificarNombreSucursal(Long sucursalId, String nombre) {
        Optional<Sucursal> sucursalOpt = sucursalRepository.findById(sucursalId);

        if (sucursalOpt.isEmpty()) {
            throw new IllegalArgumentException("La sucursal con ID " + sucursalId + " no existe.");
        }

        Sucursal sucursal = sucursalOpt.get();
       
        sucursal.setNombre(nombre);

        return sucursalRepository.save(sucursal);
    }
	
	public Producto modificarNombreProducto(Long productoId, String nombre) {
        // Buscar el producto por su ID
        Optional<Producto> productoOpt = productoRepository.findById(productoId);

        if (productoOpt.isEmpty()) {
            throw new IllegalArgumentException("La franquicia con ID " + productoId + " no existe.");
        }

        Producto producto = productoOpt.get();
       
        producto.setNombre(nombre);

        return productoRepository.save(producto);
    }

}
