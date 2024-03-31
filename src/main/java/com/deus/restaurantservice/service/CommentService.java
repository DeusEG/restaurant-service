package com.deus.restaurantservice.service;

import com.deus.restaurantservice.model.Comment;
import com.deus.restaurantservice.model.Restaurant;
import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.repository.CommentRepository;

import java.util.List;

/**
 * Сервис для работы с комментариями ресторана
 */
public interface CommentService {
    /**
     * Сервис для работы с комментариями ресторана
     */
    List<Comment> getAllCommentsByRestaurant(Restaurant restaurant);

    /**
     * Метод для создания комментария к ресторану
     *
     * @param user         Пользователь, оставляющий комментарий
     * @param restaurant   Ресторан, которому оставляют комментирий
     * @param commentText  Содержание комментария
     * @return             Возвращает объект Comment, иначе выбрасывает исключение
     */
    Comment createComment(User user, Restaurant restaurant, String commentText);

    /**
     * Метод для удаления комментария к ресторану
     *
     * @param id    Идентификатор комментария
     * @return      Идентификатор удалённого комменатрия
     */
    Long deleteCommentById(String id);
}
