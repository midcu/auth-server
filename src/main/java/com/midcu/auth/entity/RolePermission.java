package com.midcu.auth.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name="sys_role_permission")
@DynamicInsert
@DynamicUpdate
//@NamedEntityGraph(name = "rp_permission_role", attributeNodes = {
//        @NamedAttributeNode("role"),
//        @NamedAttributeNode(value = "permission", subgraph = "ac")
//},
//        subgraphs = {
//            @NamedSubgraph(name = "ac", attributeNodes = {
//                    @NamedAttributeNode(value = "name"),
//                    @NamedAttributeNode(value = "title")
//            })
//        }
//)
public class RolePermission extends BaseAuditable{

//    @ManyToOne(targetEntity = Role.class)
//    @JoinColumn(name = "role_id", referencedColumnName = "id",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
//    public Role role;
//
//    @ManyToOne(targetEntity = Permission.class)
//    @JoinColumn(name = "permission_id", referencedColumnName = "id",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
//    public Permission permission;

    public Long roleId;

    public Long permissionId;
}