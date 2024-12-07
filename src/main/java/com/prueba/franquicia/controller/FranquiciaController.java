package com.prueba.franquicia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.franquicia.entity.Franquicia;
import com.prueba.franquicia.entity.Producto;
import com.prueba.franquicia.entity.Sucursal;
import com.prueba.franquicia.service.FranquiciaService;

@RestController
@RequestMapping("/api/franquicias")
public class FranquiciaController {
	
	@Autowired
    private FranquiciaService franquiciaService;
	
	@PostMapping
    public ResponseEntity<Franquicia> agregarFranquicia(@RequestBody Franquicia franquicia) {
        return new ResponseEntity<>(franquiciaService.agregarFranquicia(franquicia), HttpStatus.CREATED);
    }
	
	@PostMapping("/sucursales/{franquiciaId}")
    public ResponseEntity<Sucursal> agregarSucursal(@PathVariable Long franquiciaId, @RequestBody Sucursal sucursal) {
		return new ResponseEntity<>(franquiciaService.agregarSucursal(franquiciaId, sucursal), HttpStatus.CREATED);
    }
	
	@PostMapping("/productos/{sucursalId}")
    public ResponseEntity<Producto> agregarProducto(@PathVariable Long sucursalId, @RequestBody Producto producto) {
		return new ResponseEntity<>(franquiciaService.agregarProducto(sucursalId, producto), HttpStatus.CREATED);
    }
	
	@DeleteMapping("/productos/{productoId}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long productoId) {
		franquiciaService.eliminarProducto(productoId);
        return ResponseEntity.noContent().build();
    }
	
	@PutMapping("/productos/stock/{productoId}")
	public ResponseEntity<Producto> modificarStock(
            @PathVariable Long productoId,
            @RequestParam Integer nuevoStock) {
		return new ResponseEntity<>(franquiciaService.modificarStock(productoId, nuevoStock), HttpStatus.CREATED); 
    }
	
	@GetMapping("/productos/mayor-stock/{franquiciaId}")
    public ResponseEntity<List<Producto>> obtenerProductoConMasStockPorSucursal(
            @PathVariable Long franquiciaId) {

        // Llamamos al servicio para obtener los productos con más stock por sucursal
        List<Producto> productosConMasStock = franquiciaService.obtenerProductoConMasStockPorSucursal(franquiciaId);

        // Retornamos la lista de productos
        return ResponseEntity.ok(productosConMasStock);
    }
	
	@PutMapping("/modificar-nombre-franquicia/{franquiciaId}")
	public ResponseEntity<Franquicia> modificarNombreFranquicia(
            @PathVariable Long franquiciaId,
            @RequestParam String nombre) {
		return new ResponseEntity<>(franquiciaService.modificarNombreFranquicia(franquiciaId, nombre), HttpStatus.CREATED); 
    }
	
	@PutMapping("/modificar-nombre-sucursal/{sucursalId}")
	public ResponseEntity<Sucursal> modificarNombreSucursal(
            @PathVariable Long sucursalId,
            @RequestParam String nombre) {
		return new ResponseEntity<>(franquiciaService.modificarNombreSucursal(sucursalId, nombre), HttpStatus.CREATED); 
    }
	
	@PutMapping("/modificar-nombre-producto/{productoId}")
	public ResponseEntity<Producto> modificarNombreProducto(
            @PathVariable Long productoId,
            @RequestParam String nombre) {
		return new ResponseEntity<>(franquiciaService.modificarNombreProducto(productoId, nombre), HttpStatus.CREATED); 
    }

}
