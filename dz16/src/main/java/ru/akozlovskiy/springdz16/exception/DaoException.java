package ru.akozlovskiy.springdz16.exception;

public class DaoException extends Exception {

	private static final long serialVersionUID = 4574619338040862697L;

	public DaoException(String s) {
		super(s);
	}

	public DaoException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public DaoException(Throwable throwable) {
		super(throwable);
	}
}
