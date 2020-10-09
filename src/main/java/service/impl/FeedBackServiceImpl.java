package service.impl;

import dao.MailDao;
import dao.impl.MailDaoImpl;
import service.FeedBackService;

public class FeedBackServiceImpl implements FeedBackService {
    private MailDao maildao = new MailDaoImpl();

    @Override
    public void feedBack(String ename, String email, String subject, String message) {
        maildao.sendMail(ename, email, subject, message);
    }
}
