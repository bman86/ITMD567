package com.outsider.javapackage.web.rest;

import com.outsider.javapackage.OutsiderTradingApp;

import com.outsider.javapackage.domain.UserPick;
import com.outsider.javapackage.domain.User;
import com.outsider.javapackage.repository.UserPickRepository;
import com.outsider.javapackage.service.UserPickService;
import com.outsider.javapackage.web.rest.errors.ExceptionTranslator;
import com.outsider.javapackage.service.dto.UserPickCriteria;
import com.outsider.javapackage.service.UserPickQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UserPickResource REST controller.
 *
 * @see UserPickResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OutsiderTradingApp.class)
public class UserPickResourceIntTest {

    private static final String DEFAULT_SYMBL = "AAAAAAAAAA";
    private static final String UPDATED_SYMBL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_OWN = false;
    private static final Boolean UPDATED_OWN = true;

    private static final Double DEFAULT_ENTRY_PRICE = 0.01D;
    private static final Double UPDATED_ENTRY_PRICE = 1D;

    private static final Boolean DEFAULT_UP = false;
    private static final Boolean UPDATED_UP = true;

    private static final Double DEFAULT_TARGET = 1D;
    private static final Double UPDATED_TARGET = 2D;

    private static final String DEFAULT_TIME = "AAAAAAAAAA";
    private static final String UPDATED_TIME = "BBBBBBBBBB";

    @Autowired
    private UserPickRepository userPickRepository;

    @Autowired
    private UserPickService userPickService;

    @Autowired
    private UserPickQueryService userPickQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUserPickMockMvc;

    private UserPick userPick;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserPickResource userPickResource = new UserPickResource(userPickService, userPickQueryService);
        this.restUserPickMockMvc = MockMvcBuilders.standaloneSetup(userPickResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserPick createEntity(EntityManager em) {
        UserPick userPick = new UserPick()
            .symbl(DEFAULT_SYMBL)
            .own(DEFAULT_OWN)
            .entryPrice(DEFAULT_ENTRY_PRICE)
            .up(DEFAULT_UP)
            .target(DEFAULT_TARGET)
            .time(DEFAULT_TIME);
        // Add required entity
        User user = UserResourceIntTest.createEntity(em);
        em.persist(user);
        em.flush();
        userPick.setUser(user);
        return userPick;
    }

    @Before
    public void initTest() {
        userPick = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserPick() throws Exception {
        int databaseSizeBeforeCreate = userPickRepository.findAll().size();

        // Create the UserPick
        restUserPickMockMvc.perform(post("/api/user-picks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userPick)))
            .andExpect(status().isCreated());

        // Validate the UserPick in the database
        List<UserPick> userPickList = userPickRepository.findAll();
        assertThat(userPickList).hasSize(databaseSizeBeforeCreate + 1);
        UserPick testUserPick = userPickList.get(userPickList.size() - 1);
        assertThat(testUserPick.getSymbl()).isEqualTo(DEFAULT_SYMBL);
        assertThat(testUserPick.isOwn()).isEqualTo(DEFAULT_OWN);
        assertThat(testUserPick.getEntryPrice()).isEqualTo(DEFAULT_ENTRY_PRICE);
        assertThat(testUserPick.isUp()).isEqualTo(DEFAULT_UP);
        assertThat(testUserPick.getTarget()).isEqualTo(DEFAULT_TARGET);
        assertThat(testUserPick.getTime()).isEqualTo(DEFAULT_TIME);
    }

    @Test
    @Transactional
    public void createUserPickWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userPickRepository.findAll().size();

        // Create the UserPick with an existing ID
        userPick.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserPickMockMvc.perform(post("/api/user-picks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userPick)))
            .andExpect(status().isBadRequest());

        // Validate the UserPick in the database
        List<UserPick> userPickList = userPickRepository.findAll();
        assertThat(userPickList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSymblIsRequired() throws Exception {
        int databaseSizeBeforeTest = userPickRepository.findAll().size();
        // set the field null
        userPick.setSymbl(null);

        // Create the UserPick, which fails.

        restUserPickMockMvc.perform(post("/api/user-picks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userPick)))
            .andExpect(status().isBadRequest());

        List<UserPick> userPickList = userPickRepository.findAll();
        assertThat(userPickList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOwnIsRequired() throws Exception {
        int databaseSizeBeforeTest = userPickRepository.findAll().size();
        // set the field null
        userPick.setOwn(null);

        // Create the UserPick, which fails.

        restUserPickMockMvc.perform(post("/api/user-picks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userPick)))
            .andExpect(status().isBadRequest());

        List<UserPick> userPickList = userPickRepository.findAll();
        assertThat(userPickList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEntryPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = userPickRepository.findAll().size();
        // set the field null
        userPick.setEntryPrice(null);

        // Create the UserPick, which fails.

        restUserPickMockMvc.perform(post("/api/user-picks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userPick)))
            .andExpect(status().isBadRequest());

        List<UserPick> userPickList = userPickRepository.findAll();
        assertThat(userPickList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpIsRequired() throws Exception {
        int databaseSizeBeforeTest = userPickRepository.findAll().size();
        // set the field null
        userPick.setUp(null);

        // Create the UserPick, which fails.

        restUserPickMockMvc.perform(post("/api/user-picks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userPick)))
            .andExpect(status().isBadRequest());

        List<UserPick> userPickList = userPickRepository.findAll();
        assertThat(userPickList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetIsRequired() throws Exception {
        int databaseSizeBeforeTest = userPickRepository.findAll().size();
        // set the field null
        userPick.setTarget(null);

        // Create the UserPick, which fails.

        restUserPickMockMvc.perform(post("/api/user-picks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userPick)))
            .andExpect(status().isBadRequest());

        List<UserPick> userPickList = userPickRepository.findAll();
        assertThat(userPickList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = userPickRepository.findAll().size();
        // set the field null
        userPick.setTime(null);

        // Create the UserPick, which fails.

        restUserPickMockMvc.perform(post("/api/user-picks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userPick)))
            .andExpect(status().isBadRequest());

        List<UserPick> userPickList = userPickRepository.findAll();
        assertThat(userPickList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUserPicks() throws Exception {
        // Initialize the database
        userPickRepository.saveAndFlush(userPick);

        // Get all the userPickList
        restUserPickMockMvc.perform(get("/api/user-picks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userPick.getId().intValue())))
            .andExpect(jsonPath("$.[*].symbl").value(hasItem(DEFAULT_SYMBL.toString())))
            .andExpect(jsonPath("$.[*].own").value(hasItem(DEFAULT_OWN.booleanValue())))
            .andExpect(jsonPath("$.[*].entryPrice").value(hasItem(DEFAULT_ENTRY_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].up").value(hasItem(DEFAULT_UP.booleanValue())))
            .andExpect(jsonPath("$.[*].target").value(hasItem(DEFAULT_TARGET.doubleValue())))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME.toString())));
    }

    @Test
    @Transactional
    public void getUserPick() throws Exception {
        // Initialize the database
        userPickRepository.saveAndFlush(userPick);

        // Get the userPick
        restUserPickMockMvc.perform(get("/api/user-picks/{id}", userPick.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userPick.getId().intValue()))
            .andExpect(jsonPath("$.symbl").value(DEFAULT_SYMBL.toString()))
            .andExpect(jsonPath("$.own").value(DEFAULT_OWN.booleanValue()))
            .andExpect(jsonPath("$.entryPrice").value(DEFAULT_ENTRY_PRICE.doubleValue()))
            .andExpect(jsonPath("$.up").value(DEFAULT_UP.booleanValue()))
            .andExpect(jsonPath("$.target").value(DEFAULT_TARGET.doubleValue()))
            .andExpect(jsonPath("$.time").value(DEFAULT_TIME.toString()));
    }

    @Test
    @Transactional
    public void getAllUserPicksBySymblIsEqualToSomething() throws Exception {
        // Initialize the database
        userPickRepository.saveAndFlush(userPick);

        // Get all the userPickList where symbl equals to DEFAULT_SYMBL
        defaultUserPickShouldBeFound("symbl.equals=" + DEFAULT_SYMBL);

        // Get all the userPickList where symbl equals to UPDATED_SYMBL
        defaultUserPickShouldNotBeFound("symbl.equals=" + UPDATED_SYMBL);
    }

    @Test
    @Transactional
    public void getAllUserPicksBySymblIsInShouldWork() throws Exception {
        // Initialize the database
        userPickRepository.saveAndFlush(userPick);

        // Get all the userPickList where symbl in DEFAULT_SYMBL or UPDATED_SYMBL
        defaultUserPickShouldBeFound("symbl.in=" + DEFAULT_SYMBL + "," + UPDATED_SYMBL);

        // Get all the userPickList where symbl equals to UPDATED_SYMBL
        defaultUserPickShouldNotBeFound("symbl.in=" + UPDATED_SYMBL);
    }

    @Test
    @Transactional
    public void getAllUserPicksBySymblIsNullOrNotNull() throws Exception {
        // Initialize the database
        userPickRepository.saveAndFlush(userPick);

        // Get all the userPickList where symbl is not null
        defaultUserPickShouldBeFound("symbl.specified=true");

        // Get all the userPickList where symbl is null
        defaultUserPickShouldNotBeFound("symbl.specified=false");
    }

    @Test
    @Transactional
    public void getAllUserPicksByOwnIsEqualToSomething() throws Exception {
        // Initialize the database
        userPickRepository.saveAndFlush(userPick);

        // Get all the userPickList where own equals to DEFAULT_OWN
        defaultUserPickShouldBeFound("own.equals=" + DEFAULT_OWN);

        // Get all the userPickList where own equals to UPDATED_OWN
        defaultUserPickShouldNotBeFound("own.equals=" + UPDATED_OWN);
    }

    @Test
    @Transactional
    public void getAllUserPicksByOwnIsInShouldWork() throws Exception {
        // Initialize the database
        userPickRepository.saveAndFlush(userPick);

        // Get all the userPickList where own in DEFAULT_OWN or UPDATED_OWN
        defaultUserPickShouldBeFound("own.in=" + DEFAULT_OWN + "," + UPDATED_OWN);

        // Get all the userPickList where own equals to UPDATED_OWN
        defaultUserPickShouldNotBeFound("own.in=" + UPDATED_OWN);
    }

    @Test
    @Transactional
    public void getAllUserPicksByOwnIsNullOrNotNull() throws Exception {
        // Initialize the database
        userPickRepository.saveAndFlush(userPick);

        // Get all the userPickList where own is not null
        defaultUserPickShouldBeFound("own.specified=true");

        // Get all the userPickList where own is null
        defaultUserPickShouldNotBeFound("own.specified=false");
    }

    @Test
    @Transactional
    public void getAllUserPicksByEntryPriceIsEqualToSomething() throws Exception {
        // Initialize the database
        userPickRepository.saveAndFlush(userPick);

        // Get all the userPickList where entryPrice equals to DEFAULT_ENTRY_PRICE
        defaultUserPickShouldBeFound("entryPrice.equals=" + DEFAULT_ENTRY_PRICE);

        // Get all the userPickList where entryPrice equals to UPDATED_ENTRY_PRICE
        defaultUserPickShouldNotBeFound("entryPrice.equals=" + UPDATED_ENTRY_PRICE);
    }

    @Test
    @Transactional
    public void getAllUserPicksByEntryPriceIsInShouldWork() throws Exception {
        // Initialize the database
        userPickRepository.saveAndFlush(userPick);

        // Get all the userPickList where entryPrice in DEFAULT_ENTRY_PRICE or UPDATED_ENTRY_PRICE
        defaultUserPickShouldBeFound("entryPrice.in=" + DEFAULT_ENTRY_PRICE + "," + UPDATED_ENTRY_PRICE);

        // Get all the userPickList where entryPrice equals to UPDATED_ENTRY_PRICE
        defaultUserPickShouldNotBeFound("entryPrice.in=" + UPDATED_ENTRY_PRICE);
    }

    @Test
    @Transactional
    public void getAllUserPicksByEntryPriceIsNullOrNotNull() throws Exception {
        // Initialize the database
        userPickRepository.saveAndFlush(userPick);

        // Get all the userPickList where entryPrice is not null
        defaultUserPickShouldBeFound("entryPrice.specified=true");

        // Get all the userPickList where entryPrice is null
        defaultUserPickShouldNotBeFound("entryPrice.specified=false");
    }

    @Test
    @Transactional
    public void getAllUserPicksByUpIsEqualToSomething() throws Exception {
        // Initialize the database
        userPickRepository.saveAndFlush(userPick);

        // Get all the userPickList where up equals to DEFAULT_UP
        defaultUserPickShouldBeFound("up.equals=" + DEFAULT_UP);

        // Get all the userPickList where up equals to UPDATED_UP
        defaultUserPickShouldNotBeFound("up.equals=" + UPDATED_UP);
    }

    @Test
    @Transactional
    public void getAllUserPicksByUpIsInShouldWork() throws Exception {
        // Initialize the database
        userPickRepository.saveAndFlush(userPick);

        // Get all the userPickList where up in DEFAULT_UP or UPDATED_UP
        defaultUserPickShouldBeFound("up.in=" + DEFAULT_UP + "," + UPDATED_UP);

        // Get all the userPickList where up equals to UPDATED_UP
        defaultUserPickShouldNotBeFound("up.in=" + UPDATED_UP);
    }

    @Test
    @Transactional
    public void getAllUserPicksByUpIsNullOrNotNull() throws Exception {
        // Initialize the database
        userPickRepository.saveAndFlush(userPick);

        // Get all the userPickList where up is not null
        defaultUserPickShouldBeFound("up.specified=true");

        // Get all the userPickList where up is null
        defaultUserPickShouldNotBeFound("up.specified=false");
    }

    @Test
    @Transactional
    public void getAllUserPicksByTargetIsEqualToSomething() throws Exception {
        // Initialize the database
        userPickRepository.saveAndFlush(userPick);

        // Get all the userPickList where target equals to DEFAULT_TARGET
        defaultUserPickShouldBeFound("target.equals=" + DEFAULT_TARGET);

        // Get all the userPickList where target equals to UPDATED_TARGET
        defaultUserPickShouldNotBeFound("target.equals=" + UPDATED_TARGET);
    }

    @Test
    @Transactional
    public void getAllUserPicksByTargetIsInShouldWork() throws Exception {
        // Initialize the database
        userPickRepository.saveAndFlush(userPick);

        // Get all the userPickList where target in DEFAULT_TARGET or UPDATED_TARGET
        defaultUserPickShouldBeFound("target.in=" + DEFAULT_TARGET + "," + UPDATED_TARGET);

        // Get all the userPickList where target equals to UPDATED_TARGET
        defaultUserPickShouldNotBeFound("target.in=" + UPDATED_TARGET);
    }

    @Test
    @Transactional
    public void getAllUserPicksByTargetIsNullOrNotNull() throws Exception {
        // Initialize the database
        userPickRepository.saveAndFlush(userPick);

        // Get all the userPickList where target is not null
        defaultUserPickShouldBeFound("target.specified=true");

        // Get all the userPickList where target is null
        defaultUserPickShouldNotBeFound("target.specified=false");
    }

    @Test
    @Transactional
    public void getAllUserPicksByTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        userPickRepository.saveAndFlush(userPick);

        // Get all the userPickList where time equals to DEFAULT_TIME
        defaultUserPickShouldBeFound("time.equals=" + DEFAULT_TIME);

        // Get all the userPickList where time equals to UPDATED_TIME
        defaultUserPickShouldNotBeFound("time.equals=" + UPDATED_TIME);
    }

    @Test
    @Transactional
    public void getAllUserPicksByTimeIsInShouldWork() throws Exception {
        // Initialize the database
        userPickRepository.saveAndFlush(userPick);

        // Get all the userPickList where time in DEFAULT_TIME or UPDATED_TIME
        defaultUserPickShouldBeFound("time.in=" + DEFAULT_TIME + "," + UPDATED_TIME);

        // Get all the userPickList where time equals to UPDATED_TIME
        defaultUserPickShouldNotBeFound("time.in=" + UPDATED_TIME);
    }

    @Test
    @Transactional
    public void getAllUserPicksByTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        userPickRepository.saveAndFlush(userPick);

        // Get all the userPickList where time is not null
        defaultUserPickShouldBeFound("time.specified=true");

        // Get all the userPickList where time is null
        defaultUserPickShouldNotBeFound("time.specified=false");
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultUserPickShouldBeFound(String filter) throws Exception {
        restUserPickMockMvc.perform(get("/api/user-picks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userPick.getId().intValue())))
            .andExpect(jsonPath("$.[*].symbl").value(hasItem(DEFAULT_SYMBL.toString())))
            .andExpect(jsonPath("$.[*].own").value(hasItem(DEFAULT_OWN.booleanValue())))
            .andExpect(jsonPath("$.[*].entryPrice").value(hasItem(DEFAULT_ENTRY_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].up").value(hasItem(DEFAULT_UP.booleanValue())))
            .andExpect(jsonPath("$.[*].target").value(hasItem(DEFAULT_TARGET.doubleValue())))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultUserPickShouldNotBeFound(String filter) throws Exception {
        restUserPickMockMvc.perform(get("/api/user-picks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingUserPick() throws Exception {
        // Get the userPick
        restUserPickMockMvc.perform(get("/api/user-picks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserPick() throws Exception {
        // Initialize the database
        userPickService.save(userPick);

        int databaseSizeBeforeUpdate = userPickRepository.findAll().size();

        // Update the userPick
        UserPick updatedUserPick = userPickRepository.findOne(userPick.getId());
        updatedUserPick
            .symbl(UPDATED_SYMBL)
            .own(UPDATED_OWN)
            .entryPrice(UPDATED_ENTRY_PRICE)
            .up(UPDATED_UP)
            .target(UPDATED_TARGET)
            .time(UPDATED_TIME);

        restUserPickMockMvc.perform(put("/api/user-picks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserPick)))
            .andExpect(status().isOk());

        // Validate the UserPick in the database
        List<UserPick> userPickList = userPickRepository.findAll();
        assertThat(userPickList).hasSize(databaseSizeBeforeUpdate);
        UserPick testUserPick = userPickList.get(userPickList.size() - 1);
        assertThat(testUserPick.getSymbl()).isEqualTo(UPDATED_SYMBL);
        assertThat(testUserPick.isOwn()).isEqualTo(UPDATED_OWN);
        assertThat(testUserPick.getEntryPrice()).isEqualTo(UPDATED_ENTRY_PRICE);
        assertThat(testUserPick.isUp()).isEqualTo(UPDATED_UP);
        assertThat(testUserPick.getTarget()).isEqualTo(UPDATED_TARGET);
        assertThat(testUserPick.getTime()).isEqualTo(UPDATED_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingUserPick() throws Exception {
        int databaseSizeBeforeUpdate = userPickRepository.findAll().size();

        // Create the UserPick

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUserPickMockMvc.perform(put("/api/user-picks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userPick)))
            .andExpect(status().isCreated());

        // Validate the UserPick in the database
        List<UserPick> userPickList = userPickRepository.findAll();
        assertThat(userPickList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUserPick() throws Exception {
        // Initialize the database
        userPickService.save(userPick);

        int databaseSizeBeforeDelete = userPickRepository.findAll().size();

        // Get the userPick
        restUserPickMockMvc.perform(delete("/api/user-picks/{id}", userPick.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserPick> userPickList = userPickRepository.findAll();
        assertThat(userPickList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserPick.class);
        UserPick userPick1 = new UserPick();
        userPick1.setId(1L);
        UserPick userPick2 = new UserPick();
        userPick2.setId(userPick1.getId());
        assertThat(userPick1).isEqualTo(userPick2);
        userPick2.setId(2L);
        assertThat(userPick1).isNotEqualTo(userPick2);
        userPick1.setId(null);
        assertThat(userPick1).isNotEqualTo(userPick2);
    }
}
