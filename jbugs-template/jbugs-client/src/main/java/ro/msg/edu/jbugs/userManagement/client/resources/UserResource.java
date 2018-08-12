package ro.msg.edu.jbugs.userManagement.client.resources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.userManagement.business.control.UserService;
import ro.msg.edu.jbugs.userManagement.business.control.UserServiceImpl;
import ro.msg.edu.jbugs.userManagement.business.dto.UserDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private static final Logger log = LogManager.getLogger(UserResource.class);

    @EJB
    UserService userService;

    @GET
    public List<UserDto> getUsers() throws BusinessException {
        return userService.getAllUsers();
    }

    @POST
    public Response login(UserDto userDto) throws BusinessException {
        log.info("login rest: userDto={}}",userDto);

        UserDto userDtoResult = userService.login(userDto.getUsername(), userDto.getPassword());

        log.info("login rest: userDto={}",userDto);

        return Response.status(Response.Status.OK).entity(userDtoResult).build();
    }


}
