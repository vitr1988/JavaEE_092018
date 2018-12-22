package ru.otus.jms.mdb;

import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(
        mappedName="jms/Topic", //имя topic или queue, на который подписан бин
        name = "ExampleMDB")
public class MessageDrivenBean implements MessageListener {

    @Resource
    MessageDrivenContext messageDrivenContext;

    //метод, вызываемый при получении нового сообщения
    @Override
    public void onMessage(Message msg) {
        try {
            if (msg instanceof TextMessage) {
                TextMessage message = (TextMessage) msg;
                //считываем свойство из соответствующего поля, заданное вручную в consumer
                System.out.println("FROM MDB - client type IS " + message.getStringProperty("clientType"));
                //считываем  само сообщение
                System.out.println("FROM MDB - payload  IS " + message.getText());
            }
        } catch (JMSException ex) {
            ex.printStackTrace();
            messageDrivenContext.setRollbackOnly(); // message will be redelivered
        }
    }

}
