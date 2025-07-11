/***************************************************************
 * Author       :	 
 * Created Date :	
 * Version      : 	
 * History  :	
 * *************************************************************/
package com.jackson.jwt_auth.with_refreh_token.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * UserEntity Class.
 * <p>
 * </p>
 *
 * @author
 */
@Entity
@Table(name = "tbl_users")
@Data
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {
}
