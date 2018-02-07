package sat.maranatha.oop2.dao;

import java.util.List;

/**
 *
 * @author Anthony-1572010
 */
public interface DaoService<E> {
    int addData(E object);
    int deleteData(E object);
    int updateData(E object);
    List<E> showAllData();
}
