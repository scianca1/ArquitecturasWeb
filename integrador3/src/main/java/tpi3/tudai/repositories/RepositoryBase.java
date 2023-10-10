package tpi3.tudai.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface RepositoryBase<T,ID extends Serializable> extends org.springframework.data.repository.Repository<T,ID> {
    /**
     * Elimina una entidad de la BD.
     * @param deleted entidad a borrar.
     */
    void delete( T deleted);

    /**
     * Retorna un listado de las entidades almacenadas.
     * @return Listado de entidades.
     */
    List<T> findAll();

    /**
     * Busca y devuelve una entidad de acuerdo al id ingresado por parámetro.
     * @param id Identificador único de la entidad.
     * @return Entidad que coicide con el id ingresado.
     */
    Optional<T> findById(Integer id);

    /**
     * Indica si existe la entidad con el id ingresado por parámetro.
     * @param id Identificador único de la entidad.
     * @return True en caso de existir, caso contraio, false.
     */


    /**
     * Elimina una entidad correspondiente al id ingresado por parámetro.
     * @param id Identificador único de la entidad.
     */
    void deleteById(Integer id);

    /**
     * Persiste una entidad ingresada por parámetro.
     * @param persisted entidad a persistir
     * @return retorna la entidad persistida con el id asignado.
     */
    T save( T persisted);

}
