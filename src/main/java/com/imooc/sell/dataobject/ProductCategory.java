package com.imooc.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Description: 商品类目实体类
 * @author: zr
 * @date: 2020/4/2 21:05
 */
//  Java Persistence API定义了一种定义，可以将常规的普通Java对象（有时被称作POJO）映射到数据库。
//  这些普通Java对象被称作Entity Bean。
//  除了是用Java Persistence元数据将其映射到数据库外，Entity Bean与其他Java类没有任何区别。
//  事实上，创建一个Entity Bean对象相当于新建一条记录，删除一个Entity Bean会同时从数据库中删除对应记录，修改一个Entity Bean时，容器会自动将Entity Bean的状态和数据库同步。
//  Java Persistence API还定义了一种查询语言（JPQL），具有与SQL相类似的特征，只不过做了裁减，以便处理Java对象而非原始的关系表。

// @Entity 表明该类 (UserEntity) 为一个实体类，它默认对应数据库中的表名是user_entity。这里也可以写成
//
//  　　@Entity(name = "xwj_user")
//
//  　　　　　　或者
//
//  　　　　　　@Entity
// @Table(name = "xwj_user", schema ="")
//
//  　查看@Entity注解，发现其只有一个属性name，表示其所对应的数据库中的表名
//
//  　　@Table 当实体类与其映射的数据库表名不同名时需要使用 @Table注解说明，该标注与 @Entity 注解并列使用，置于实体类声明语句之前，可写于单独语　　　　　　　　　　句行，也可与声明语句同行。
//  　　　　　　@Table注解的常用选项是 name，用于指明数据库的表名
//  　　　　　　@Table注解还有两个选项 catalog 和 schema 用于设置表所属的数据库目录或模式，通常为数据库名
//
//@Id注释指定表的主键，它可以有多种生成方式：
//        　　1）TABLE：容器指定用底层的数据表确保唯一；
//        　　2）SEQUENCE：使用数据库德SEQUENCE列莱保证唯一（Oracle数据库通过序列来生成唯一ID）；
//        　　3）IDENTITY：使用数据库的IDENTITY列莱保证唯一；
//        　　4）AUTO：由容器挑选一个合适的方式来保证唯一；
//        　　5）NONE：容器不负责主键的生成，由程序来完成。
//
//@DynamicUpdate：当value为true时，一个表有很多字段，当我们只更新一个字段时，比如username
//
//        就会只更新一个字段，而不是全部更新，这样执行速度就提升了。
//
//        当value为false时，则更新全部字段，这样执行速度就下降了。
@Entity
@DynamicUpdate
@Data
public class ProductCategory {

    /*目录 ID */
    @Id                     //表示为主键
    @GeneratedValue         //表示自增
    private Integer categoryId;

    /* 类目名称 */
    private String categoryName;

    /* 类目编号 */
    private Integer categoryType;


    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    public ProductCategory() {
    }
}
