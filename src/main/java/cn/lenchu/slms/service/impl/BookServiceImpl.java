package cn.lenchu.slms.service.impl;

import cn.lenchu.slms.entity.Book;
import cn.lenchu.slms.entity.Type;
import cn.lenchu.slms.repository.BookRepository;
import cn.lenchu.slms.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class BookServiceImpl implements cn.lenchu.slms.service.BookService {
    private final BookRepository bookDao;
    private final TypeRepository typeDao;

    @Autowired
    public BookServiceImpl(BookRepository bookDao, TypeRepository typeDao) {
        this.bookDao = bookDao;
        this.typeDao = typeDao;
    }

    /**
     * 2.1添加或修改图书
     * @param book Book对象 有id是修改，无id是添加
     * @return 修改后的Book对象
     */
    @Override
    public Book saveOrUpdateBook(Book book) {
        return this.bookDao.save(book);
    }

    /**
     * 2.2删除图书
     * @param book Book对象
     */
    @Override
    public void deleteBook(Book book) {
        this.bookDao.delete(book);
    }

    /**
     * 2.3通过id查询图书
     * @param id id
     * @return Book
     */
    @Override
    public Book findBookById(Integer id) {
        return this.bookDao.findById(id).get();
    }

    /**
     * 2.4通过书名模糊查询
     * @param name 书名
     * @param pageable 分页模型
     * @return 分页对象
     */
    @Override
    public Page<Book> findBookByNameLike(String name, Pageable pageable) {
        return this.bookDao.findByNameLike(this.transferStringToLike(name), pageable);
    }

    /**
     * 2.5通过作者模糊查询
     * @param author 作者
     * @param pageable 分页模型
     * @return 分页对象
     */
    @Override
    public Page<Book> findBookByAuthorLike(String author, Pageable pageable) {
        return this.bookDao.findByAuthorLike(this.transferStringToLike(author), pageable);
    }

    /**
     * 2.6查询所有图书
     * @param pageable 分页模型
     * @return 分页对象
     */
    @Override
    public Page<Book> findAllBook(Pageable pageable) {
        return this.bookDao.findAll(pageable);
    }

    /**
     * 2.7给图书添加类别
     * @param bookId
     * @param typeId
     */
    @Override
    public void addBookType(Integer bookId, Integer typeId) {
        Book book = this.findBookById(bookId);
        Set<Type> types = book.getTypes();
        Type type = this.typeDao.findById(typeId).get();
        types.add(type);
        book.setTypes(types);
        this.bookDao.save(book);
    }

    /**
     * 2.8删除图书的指定类别
     * @param bookId
     * @param typeId
     */
    @Override
    public void deleteBookType(Integer bookId, Integer typeId) {
        Book book = this.findBookById(bookId);
        Set<Type> types = book.getTypes();
        Type type = this.typeDao.findById(typeId).get();
        types.remove(type);
        book.setTypes(types);
        this.bookDao.save(book);
    }

    /**
     * 2.9查询指定类别的图书
     * @param typeId
     * @param pageable 分页模型
     * @return 分页对象
     */
    @Override
    public Page<Book> findBookByTpyeId(Integer typeId, Pageable pageable) {
        Type type = this.typeDao.findById(typeId).get();
        Book b = new Book();
        Set<Type> types = new HashSet<>();
        types.add(type);
        b.setTypes(types);
        Example<Book> example = Example.of(b);
        return this.bookDao.findAll(example, pageable);
    }

    /**
     * 2.10添加图书类型
     * @param typename 类型名
     * @return Type对象
     */
    @Override
    public Type saveBookType(String typename) {
        return this.typeDao.save(new Type(typename));
    }

    /**
     * 2.11删除图书类型
     * @param id id
     */
    @Override
    public void deleteBookType(Integer id) {
        this.typeDao.deleteById(id);
    }

    /**
     * 2.12查询所有类型
     * @param pageable 分页模型
     * @return 分页对象
     */
    @Override
    public Page<Type> findAllType(Pageable pageable) {
        return this.typeDao.findAll(pageable);
    }

    /**
     * 2.13查询图书的所有类型
     * @param id BookId
     * @return Type集合
     */
    @Override
    public Set<Type> findAllTypeByBookId(Integer id) {
        return this.findBookById(id).getTypes();
    }

    /**
     * 转换字符串
     * str => %s%t%r%
     * @param org 原始字符串
     * @return 转换后的字符串
     */
    private String transferStringToLike(String org) {
        char[] cs = org.toCharArray();
        char[] newcs = new char[2 * cs.length + 1];
        for (int i = 0; i < newcs.length; i++)
            newcs[i] = '%';
        for (int i = 0; i < cs.length; i++) {
            newcs[i * 2 + 1] = cs[i];
        }
        return new String(newcs);
    }
}
