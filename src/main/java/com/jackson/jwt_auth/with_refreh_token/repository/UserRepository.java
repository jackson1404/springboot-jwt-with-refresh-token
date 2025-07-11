/***************************************************************
 * Author       :	 
 * Created Date :	
 * Version      : 	
 * History  :	
 * *************************************************************/
package com.jackson.jwt_auth.with_refreh_token.repository;

import com.jackson.jwt_auth.with_refreh_token.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * UserRepository Class.
 * <p>
 * </p>
 *
 * @author
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
