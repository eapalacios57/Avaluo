package com.asesoftware.util.cache;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;


import com.asesoftware.util.lang.UtilTexto;

/**
 * Creacion de una fabrica para la administracion de cache dentro de los
 * proyectos internos, esta nos permitira gestionar las operaciones de la
 * misma(agregar objeto, eliminar objeto) y agregar nuevas caches según como se
 * considere necesario.
 *
 * @author Said Tilaguy
 *
 */
public class UtilCache {

	// -------------------------------------------------------------------------
	// ATRIBUTOS
	// -------------------------------------------------------------------------
	/**
	 * Representa la instancia de la api para la utlizacion de la cache
	 */
	private static CacheManager cacheManager;

	/**
	 * Contiene todas las regiones de cache que crearemos en el sistema.
	 */
	@SuppressWarnings("rawtypes")
	private static Map<String, Cache> regiones;

	/**
	 * Representa la instancia singleton de la fabrica
	 */
	private static UtilCache cacheFabrica;

	// -------------------------------------------------------------------------
	// METODOS
	// -------------------------------------------------------------------------
	/**
	 * Se encarga de generar la instancia de la fabrica de cache si esta ya
	 * existe la retorna de los contrario crea una nueva instancia
	 *
	 * @return instancia de la clase CacheFabrica
	 */
	public static UtilCache getInstance() {
		if (cacheFabrica == null) {
			synchronized (UtilCache.class) {
				if (cacheFabrica == null) {
					// se crea en memoria el manejador de cache y se inicializa.
					cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
					cacheManager.init();
					// Instancia el objeto de tipo CacheFabrica
					regiones = new HashMap<>();
					cacheFabrica = new UtilCache();
				}
			}
		}
		return cacheFabrica;
	}

	/**
	 * Permite crear una region nueva para el adminsitrador de cache.
	 *
	 * @param region:
	 *            define el nombre de la region con la cual ser recuperara el
	 *            cache.
	 * @param llave:
	 *            define la clase o tipo de la llave que identifica los valores
	 *            almacenados en la region de la cache.
	 * @param valor:
	 *            define la clase o tipo de valores que seran almacenados en la
	 *            region de la cache.
	 * @param heap:
	 *            define el limite de elementos que puede tener la region, por
	 *            defecto el valor es 10.
	 * @return devuelva la region de cache que se agrego al administrador.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unlikely-arg-type" })
	public Cache agregarRegion(String region, Class llave, Class valor, Integer limite) {
		/*
		 * Se recupera el cachï¿½ con su alias, tipo de clave y tipo de valor a
		 * CacheManager. Por ejemplo, para obtener el cachï¿½
		 * REGION_CACHE_AVALUOS necesita su alias: = "CACHE_AVALUOS", keyType =
		 * String.class y valueType = Object.class. Por seguridad los tipos de
		 * clave y valos deben ser iguales a los definidos al crear la region.
		 * Si estos difieren de los que se esperan el CacheManager lanza una
		 * ClassCastException. Esto protege el cachï¿½ de ser contaminado por
		 * tipos aleatorios.
		 */
		if (regiones.get(region) != null)
			return regiones.get(llave);
		if (cacheManager.getCache(region, llave, valor) == null)
			cacheManager.createCache(region, CacheConfigurationBuilder.newCacheConfigurationBuilder(llave, valor,
					ResourcePoolsBuilder.heap(limite == null ? 1000 : limite)).build());
		Cache retorno = cacheManager.getCache(region, llave, valor);
		regiones.put(region, retorno);
		return retorno;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked", "unlikely-arg-type" })
	public Cache agregarRegion(String region, Class llave, Class valor, Integer limite, long tiempoExpiracion) {
		/*
		 * Se recupera el cachï¿½ con su alias, tipo de clave y tipo de valor a
		 * CacheManager. Por ejemplo, para obtener el cachï¿½
		 * REGION_CACHE_AVALUOS necesita su alias: = "CACHE_AVALUOS", keyType =
		 * String.class y valueType = Object.class. Por seguridad los tipos de
		 * clave y valos deben ser iguales a los definidos al crear la region.
		 * Si estos difieren de los que se esperan el CacheManager lanza una
		 * ClassCastException. Esto protege el cachï¿½ de ser contaminado por
		 * tipos aleatorios.
		 */
		if (regiones.get(region) != null)
			return regiones.get(llave);
		if (cacheManager.getCache(region, llave, valor) == null)
			cacheManager.createCache(region, CacheConfigurationBuilder.newCacheConfigurationBuilder(llave, valor,
					ResourcePoolsBuilder.heap(limite == null ? 1000 : limite)).withExpiry(Expirations.timeToLiveExpiration(Duration.of(tiempoExpiracion, TimeUnit.SECONDS))).build());
		Cache retorno = cacheManager.getCache(region, llave, valor);
		regiones.put(region, retorno);
		return retorno;
	}

	public boolean contieneRegion(String region) {
		return regiones != null && regiones.containsKey(region);
	}

	/**
	 * Permite obtener una region del administrador de cache, si la region no
	 * existe se agrega una por defecto con el nombre definido y de tipo
	 * <String, Object>
	 *
	 * @param region:
	 *            identificador de la region.
	 * @return region de cache que pertenece al identificador.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private synchronized Cache obtenerCache(String region) {
		Cache<String, Object> cache = regiones.get(region);
		if (cache == null) {
			return agregarRegion(region, String.class, Object.class, null);
		}
		return cache;
	}
	
	
	/**
	 * Permite agregar un objeto a una region de cache determinada.
	 *
	 * @param region:
	 *            identificador de la región a la que se agregara.
	 * @param llave:
	 *            identificador del elemento a agregar en la region
	 * @param valor:
	 *            parametro que sera almacenado en la region de cache
	 *            determinada.
	 */
	@SuppressWarnings("unchecked")
	public synchronized void agregarObjeto(String region, String llave, Object valor) {
		if (UtilTexto.estaVacio(region) || UtilTexto.estaVacio(llave) || valor == null)
			return;
		obtenerCache(region).put(llave, valor);
	}

	/**
	 * Permite obtener un elemento de una region determinada de la cache.
	 *
	 * @param region:
	 *            identificador de la cache a consultar.
	 * @param llave:
	 *            identificador del elemento a cargar.
	 * @return el elemento consultado si este existe en el sistema de lo
	 *         contrario retornara vacio.
	 */
	@SuppressWarnings("unchecked")
	public synchronized Object obtenerObjeto(String region, String llave) {
		if (UtilTexto.estaVacio(region) || UtilTexto.estaVacio(llave))
			return null;
		return obtenerCache(region).get(llave);
	}

	/**
	 * Permite obtener un listado de una region si el parametro almacenado es
	 * una lista.
	 *
	 * @param region:
	 *            identificador de la region de la que se consultara el listado.
	 * @param llave:
	 *            identificador del listado en cache.
	 * @return Si el parametro es una lista el sistema la devolverá, de lo
	 *         contrario retornara vacio incluso si el elemento existe pero es
	 *         de otro tipo.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public synchronized List obtenerLista(String region, String llave) {
		if (UtilTexto.estaVacio(region) || UtilTexto.estaVacio(llave))
			return null;
		Object lista = obtenerCache(region).get(llave);
		if (lista instanceof List)
			return (List) lista;
		return null;
	}

	/***
	 * Borra una region determinada del sistema y todos los objetos cargados en
	 * cache para la misma.
	 *
	 * @param region:
	 *            identificador de la region a borrar.
	 */
	public synchronized void borrarCache(String region) {
		if (UtilTexto.estaVacio(region))
			return;
		cacheManager.removeCache(region);
	}

	/**
	 * Cierra todas la instancias de cache existentes en la aplicacion limpiando
	 * por completo la memoria
	 */
	public synchronized void cerrarCache() {
		cacheManager.close();
	}

}
