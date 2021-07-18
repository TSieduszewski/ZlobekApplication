package com.app.zlobek.util.messages;

import com.app.zlobek.entity.Message;
import lombok.Data;

import java.util.List;

@Data
public class MessageWithReceivers{

    private int idOfSingleParents;
    private Message message;
    private List<Integer> idOfSelectedParents;

    public MessageWithReceivers() {
    }

    public MessageWithReceivers(int idOfSingleParents, Message message){
        this.idOfSingleParents=idOfSingleParents;
        this.message = message;
    }
    public MessageWithReceivers(int idOfSingleParents){
        this.idOfSingleParents=idOfSingleParents;
        this.message = new Message();
    }
}
