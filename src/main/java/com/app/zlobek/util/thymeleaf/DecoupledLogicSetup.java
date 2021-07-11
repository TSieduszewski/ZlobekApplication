package com.app.zlobek.util.thymeleaf;

import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import javax.annotation.PostConstruct;

@Component
public class DecoupledLogicSetup {

    private final SpringResourceTemplateResolver templateResolver;

    public DecoupledLogicSetup(SpringResourceTemplateResolver templateResolver) {
        this.templateResolver = templateResolver;
    }

    @PostConstruct
    public void init(){
        templateResolver.setUseDecoupledLogic(true);
    }
}
