package org.alvin.cishan.sys.service.dict;

    
    
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
* @类说明: 类型字典--实体类
* @author: 唐植超
* @date : 2018-10-20 11:34:30
**/
@Setter
@Getter
@Accessors(chain = true)
@Builder	
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Dict", description = "类型字典实体")
public class Dict {

    //数据库中的字段
    
            @ApiModelProperty(value = "主键", dataType = "Long")
    private Long id ;//  主键
    
            @ApiModelProperty(value = "type", dataType = "Integer")
    private Integer type ;//  type
    
            @ApiModelProperty(value = "name", dataType = "String")
    private String name ;//  name
    
    //此处可添加查询显示辅助字段

}