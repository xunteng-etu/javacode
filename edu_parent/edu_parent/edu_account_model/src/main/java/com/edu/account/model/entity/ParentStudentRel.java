package com.edu.account.model.entity;

import com.edu.base.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: edu_parent
 * @description: 家长学生关系表实体类
 * @author: BaronLi
 * @create: 2019-08-01 11:27
 */
@Data
public class ParentStudentRel extends BaseEntity implements Serializable {
    private String relID;
    private String parentID;
    private String studentID;
    //家长与子女关系（1：母，2：父，3：祖母，4：祖父，5：外婆：6：外公，9：其他亲戚）
    private String ref;
}
