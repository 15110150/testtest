package server.crm.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import server.crm.entities.Role;
import server.crm.exceptions.CustomNotFoundException;
import server.crm.modules.users.services.roles.RoleService;

@Component
public class InitDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder (){return new BCryptPasswordEncoder();}

    @Autowired
    private RoleService roleService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Role roleAdmin = createRoleIfNotFound("ROLE_ADMIN");
        Role roleUser = createRoleIfNotFound("ROLE_USER");
    }

    // TODO : init basic data
    /**
     * @author : trungntm
     * @Date : 1/11/2018 23h06
     * */
    @Transactional
    Role createRoleIfNotFound(String roleName){
        Role role;
        try{
            role = roleService.retrieveRoleByRoleName(roleName);
        } catch (CustomNotFoundException ex) {
            Role newRole = new Role();
            newRole.setRoleName(roleName);
            return roleService.saveRole(newRole);
        }
        return role;
    }

}
