package com.app.zlobek.util.messages;

import com.app.zlobek.entity.Message;
import com.app.zlobek.entity.Parent;
import lombok.Data;

import java.util.List;

@Data
public class MessageWithReceivers{

    private int idOfSingleParents;
    private Message message;
    private boolean singleParentStatus;
    private Integer[] selectedParents;

    public MessageWithReceivers() {
        this.message = new Message();
        this.singleParentStatus=false;
    }


    public MessageWithReceivers(int idOfSingleParents){
        this.idOfSingleParents=idOfSingleParents;
        this.message = new Message();
        this.singleParentStatus=true;
    }

}
