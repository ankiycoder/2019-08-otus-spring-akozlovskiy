package ru.akozlovskiy.library.mq;

import ru.akozlovskiy.library.domain.Book;

public interface UserActivityEmitterService {

	void emitAppUserActivity(Book book);

}