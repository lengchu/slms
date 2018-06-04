package cn.lenchu.slms.service;

import cn.lenchu.slms.entity.Book;
import cn.lenchu.slms.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface BookService {
    /**
     * 2.1添加或修改图书
     * @param book Book对象 有id是修改，无id是添加
     * @return 修改后的Book对象
     */
    Book saveOrUpdateBook(Book book);

    /**
     * 2.2删除图书
     * @param book Book对象
     */
    void deleteBook(Book book);

    /**
     * 2.3通过id查询图书
     * @param id id
     * @return Book
     */
    Book findBookById(Integer id);

    /**
     * 2.4通过书名模糊查询
     * @param name 书名
     * @param pageable 分页模型
     * @return 分页对象
     */
    Page<Book> findBookByNameLike(String name, Pageable pageable);

    /**
     * 2.5通过作者模糊查询
     * @param author 作者
     * @param pageable 分页模型
     * @return 分页对象
     */
    Page<Book> findBookByAuthorLike(String author, Pageable pageable);

    /**
     * 2.6查询所有图书
     * @param pageable 分页模型
     * @return 分页对象
     */
    Page<Book> findAllBook(Pageable pageable);

    /**
     * 2.7给图书添加类别
     * @param bookId
     * @param typeId
     */
    void addBookType(Integer bookId, Integer typeId);

    /**
     * 2.8删除图书的指定类别
     * @param bookId
     * @param typeId
     */
    void deleteBookType(Integer bookId, Integer typeId);

    /**
     * 2.9查询指定类别的图书
     * @param typeId
     * @param pageable 分页模型
     * @return 分页对象
     */
    Page<Book> findBookByTpyeId(Integer typeId, Pageable pageable);

    /**
     * 2.10添加图书类型
     * @param typename 类型名
     * @return Type对象
     */
    Type saveBookType(String typename);

    /**
     * 2.11删除图书类型
     * @param id id
     */
    void deleteBookType(Integer id);

    /**
     * 2.12查询所有类型
     * @param pageable 分页模型
     * @return 分页对象
     */
    Page<Type> findAllType(Pageable pageable);

    /**
     * 2.13查询图书的所有类型
     * @param id BookId
     * @return Type集合
     */
    Set<Type> findAllTypeByBookId(Integer id);
}
