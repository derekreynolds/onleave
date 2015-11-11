package com.evolvingreality.onleave.web.rest;

import com.evolvingreality.onleave.model.Entitlement;
import com.evolvingreality.onleave.model.User;
import com.evolvingreality.onleave.service.EntitlementServiceImpl;
import com.evolvingreality.onleave.service.EntitlementTestUtil;
import com.evolvingreality.onleave.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Test class for the EntitlementResource REST controller.
 *
 * @see EntitlementResource
 */
public class EntitlementResourceTest {

    @Mock
    private EntitlementServiceImpl entitlementService;

    @Mock
    private UserService userService;
    
    private MockMvc restMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        EntitlementResource entitlementResource = new EntitlementResource(entitlementService, userService);

        this.restMvc = MockMvcBuilders.standaloneSetup(entitlementResource)
        		.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();

    }

    @Test
    public void testGetAll() throws Exception {
    	
    	Entitlement entitlement1 = EntitlementTestUtil.create();
    	Entitlement entitlement2 = EntitlementTestUtil.create();
    	
    	List<Entitlement> entitlements = new ArrayList<>();
    	entitlements.add(entitlement1);
    	entitlements.add(entitlement2);
    	
    	Page<Entitlement> page = new PageImpl<>(entitlements);
    	
    	doReturn(page).when(entitlementService).getPage(any());
    	
    	restMvc.perform(get("/api/v1.0/entitlement")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(2))
                /*.andDo(print())*/;
    }
    
    @Test
    public void testGetOne() throws Exception {
    	
    	Optional<Entitlement> entitlement = Optional.of(EntitlementTestUtil.create());
    	    	    	
    	doReturn(entitlement).when(entitlementService).get(1L);
    	  	
    	restMvc.perform(get("/api/v1.0/entitlement/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.year").value(2015))
                /*.andDo(print())*/;
    }
    
    @Test
    public void testPost() throws Exception {
    	
    	Entitlement entitlement = EntitlementTestUtil.create();
    	    
    	User u = new User();
    	u.setId(1L);
    	
    	Optional<User> user = Optional.of(u);
    	
    	doReturn(user).when(userService).get(1L);    	
    	
    	doAnswer(new Answer<Entitlement>() {
            public Entitlement answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                if (args[0] instanceof Entitlement)
                	((Entitlement)args[0]).setId(1L);
                
                return null;
            }
        }).when(entitlementService).save(any(Entitlement.class));    	
    	
    	restMvc.perform(post("/api/v1.0/user/1/entitlement")
    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
    			.content(TestUtil.convertObjectToJsonBytes(entitlement))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/api/v1.0/entitlement/1"))
                /*.andDo(print())*/;
    	
    	verify(entitlementService, times(1)).save(any(Entitlement.class));
    }
    
    @Test
    public void testPostConflict() throws Exception {
    	
    	Entitlement entitlement = EntitlementTestUtil.create();
    	    
    	User u = new User();
    	u.setId(1L);    	
    	
    	Optional<User> user = Optional.of(u);
    	
    	doReturn(user).when(userService).get(1L);
    	
    	doReturn(Boolean.TRUE).when(entitlementService).hasUserEntitlementForYear(any(), any());
    	    	
    	restMvc.perform(post("/api/v1.0/user/1/entitlement")
    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
    			.content(TestUtil.convertObjectToJsonBytes(entitlement))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                /*.andDo(print())*/;
    }
    
    @Test
    public void testPut() throws Exception {
    	
    	Entitlement entitlement = EntitlementTestUtil.create();    	    
   	
    	restMvc.perform(put("/api/v1.0/entitlement")
    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
    			.content(TestUtil.convertObjectToJsonBytes(entitlement))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                /*.andDo(print())*/;
    	
    	verify(entitlementService, times(1)).save(any(Entitlement.class));
    }
    
    @Test
    public void testDelete() throws Exception {
    	   	
    	restMvc.perform(delete("/api/v1.0/entitlement/1")
    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                /*.andDo(print())*/;
    	
    	verify(entitlementService, times(1)).delete(anyLong());
    }

}
