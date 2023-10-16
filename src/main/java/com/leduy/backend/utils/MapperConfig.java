package com.leduy.backend.utils;

import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        // Cấu hình cho trường có giá trị null sẽ bị bỏ qua trong quá trình ánh xạ
        mapper.getConfiguration().setPropertyCondition(new Condition<Object, Object>() {
            @Override
            public boolean applies(MappingContext<Object, Object> mappingContext) {
                return mappingContext.getSource() != null;
            }

        });
        return mapper;
    }
}
