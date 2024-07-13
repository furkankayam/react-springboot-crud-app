package com.furkankayam;

import com.furkankayam.dto.UserRequestResponseDto;
import com.furkankayam.dto.converter.UserConverter;
import com.furkankayam.exception.UserNotFoundExcaption;
import com.furkankayam.model.User;
import com.furkankayam.repository.UserRepository;
import com.furkankayam.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    
    // best practice name -> System Under Test (Sistem test ediliyor) (sut)
    // private UserService sut;
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserConverter userConverter;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Test
    @DisplayName("When Save User With Username Then User Exists")
    void whenSaveUserWithUsername_thenUserExists() {

        // given
        // Test verilerinin hazirlanmasi
        User userDummy = DataGenerator.generateUser();

        // mock the calls
        // Bagimli servislerin davranislarinin belirlenmesi
        // userRepository.findByUsername() metodu çağrıldığında dummyUser'ı döndürmesini sağla
        // abc metodu cagrildiginda abc dondur
        // thenReturn -> bir yonte cagrildiginda neyin dondurulmesi gerektigini belirtir
        when(userRepository.findByUsername(
                anyString())
        ).thenReturn(Optional.of(userDummy));

        // when
        // Test edilecek metodun calistirilmasi
        User user = userService.isThereUser("Test-Username");

        //then
        // Test sonuclarinin karsilastirilmasi
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("Test-Username");

        // verify
        // Sahte nesnenin belirli bir yönteminin çağrılıp çağrılmadığını belirler.
        // Bagimli servislerin calistirilmasinin kontrol edilmesi
        // yada 1 kez calistirmak icin bu
        // verify(userRepository).findByUsername(anyString());
        verify(userRepository, times(1)).findByUsername(anyString());
    }

    @Test
    @DisplayName("When User Not Found Then Throw Exception")
    void whenUserNotFound_thenThrowException() {
        // given
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        // when // then
        assertThatThrownBy(() -> userService.isThereUser("nonexistentuser"))
                .isInstanceOf(UserNotFoundExcaption.class)
                .hasMessageContaining("User with username nonexistentuser not found");

        // verify
        // metod hic calismadi -> verify(userRepository, never()).findByUsername(anyString());
        verify(userRepository).findByUsername(anyString());
    }

    @Test
    @DisplayName("When UserDto Is Given Then User Is Converted Successfully")
    void whenUserDtoIsGiven_thenUserIsConvertedSuccessfully() {

        //given
        UserRequestResponseDto userDto = DataGenerator.generateUserRequestResponseDto();
        User expected = new User();
        expected.setUsername(userDto.username());
        expected.setFirstName(userDto.firstName());
        expected.setLastName(userDto.lastName());
        expected.setEmail(userDto.email());

        //mock the calls
        when(userConverter.toUserRequestResponseDto(userDto)).thenReturn(expected);

        //when
        User user = userConverter.toUserRequestResponseDto(userDto);

        //then
        assertThat(user)
                .isNotNull()
                .extracting(User::getUsername,
                        User::getFirstName,
                        User::getLastName,
                        User::getEmail)
                .containsExactly(userDto.username(),
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.email());

    }

    @Test
    @DisplayName("When User Is Given Then UserDto Is Converted Successfully")
    void whenUserIsGiven_thenUserDtoIsConvertedSuccessfully() {

        //given
        User user = DataGenerator.generateUser();

        UserRequestResponseDto expectedDto = new UserRequestResponseDto(
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail()
        );

        //mock the class
        when(userConverter.toUserRequestResponseDto(user)).thenReturn(expectedDto);

        UserRequestResponseDto userDto = userConverter.toUserRequestResponseDto(user);

        //then
        assertThat(userDto)
                .isNotNull()
                .extracting(
                        UserRequestResponseDto::firstName,
                        UserRequestResponseDto::lastName,
                        UserRequestResponseDto::username,
                        UserRequestResponseDto::email
                )
                .containsExactly(
                        user.getFirstName(),
                        user.getLastName(),
                        user.getUsername(),
                        user.getEmail()
                );

    }

    @Test
    @DisplayName("When UserList Is Given Then UserDtoList Is Converted Successfully")
    void whenUserListIsGiven_thenUserDtoListIsConvertedSuccessfully() {

        //given
        List<User> userList = DataGenerator.generateUserList();

        //when
        List<UserRequestResponseDto> userDtoList = userConverter.toUserRequestResponseDtoList(userList);

        //then
        /*assertThat(userDtoList)
                .isNotNull()
                .hasSameSizeAs(userList);

        assertThat(userDtoList)
                .extracting(
                        UserRequestResponseDto::firstName
                )
                .containsExactlyElementsOf(
                        userList.stream()
                                .map(User::getFirstName).toList()
                );

        assertThat(userDtoList)
                .extracting(
                        UserRequestResponseDto::lastName
                )
                .containsExactlyElementsOf(
                        userList.stream()
                                .map(User::getLastName).toList()
                );

        assertThat(userDtoList)
                .extracting(
                        UserRequestResponseDto::username
                )
                .containsExactlyElementsOf(
                        userList.stream()
                                .map(User::getUsername).toList()
                );

        assertThat(userDtoList)
                .extracting(
                        UserRequestResponseDto::email
                )
                .containsExactlyElementsOf(
                        userList.stream()
                                .map(User::getEmail).toList()
                );*/

        // allStaisfy --> hepsi dogru
        // isIn  -->  bir ogenin bir listede olup olmadigi
        assertThat(userDtoList).allSatisfy(dto -> {
            assertThat(dto.firstName()).isIn(userList.stream().map(User::getFirstName).toList());
            assertThat(dto.lastName()).isIn(userList.stream().map(User::getLastName).toList());
            assertThat(dto.username()).isIn(userList.stream().map(User::getUsername).toList());
            assertThat(dto.email()).isIn(userList.stream().map(User::getEmail).toList());
        });

    }

    @Test
    @DisplayName("When Valid UserRequest Is Given Then User Is Created And Returned")
    void whenValidUserRequestIsGiven_thenUserIsCreatedAndReturned() {

        //given
        UserRequestResponseDto userRequestResponseDto = DataGenerator.generateUserRequestResponseDto();

        //mock the calls
        when(userService.createUser(userRequestResponseDto)).thenReturn(any());

        //when
        UserRequestResponseDto user = userService.createUser(userRequestResponseDto);

        //then
        assertThat(user)
                .isNotNull()
                .isEqualTo(userRequestResponseDto);

        //verify
        verify(userRepository, times(1)).save(any());

    }

    @Test
    @DisplayName("When Valid Username Is Given Then User Is Deleted And Success Response Is Returned")
    void whenValidUsernameIsGiven_thenUserIsDeletedAndSuccessResponseIsReturned() {

        //given
        String username = "furkan";
        User user = DataGenerator.generateUser();

        //mock the calls
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).deleteById(user.getId());

        //when
        Map<String, Boolean> response = userService.deleteUser(username);

        //then
        assertThat(response)
                .isNotNull()
                .hasSize(1)
                .containsEntry("Delete: ", true);

        //verify
        verify(userRepository).findByUsername(any());
        verify(userRepository).deleteById(any());

    }

    @Test
    @DisplayName("When Get All Users Is Called Then List Of Users Is Returned")
    void whenGetAllUsersIsCalled_thenListOfUsersIsReturned() {

        //given
        List<User> users = DataGenerator.generateUserList();

        List<UserRequestResponseDto> expectedDtoList = users.stream()
                .map(u -> userConverter.toUserRequestResponseDto(u))
                .toList();

        //mock the calls
        when(userRepository.findAll()).thenReturn(users);

        //when
        List<UserRequestResponseDto> actualDtoList = userService.getAllUsers();

        //then
        assertThat(actualDtoList).allSatisfy(dto -> {
            assertThat(dto.firstName()).isIn(expectedDtoList.stream().map(UserRequestResponseDto::firstName).toList());
            assertThat(dto.lastName()).isIn(expectedDtoList.stream().map(UserRequestResponseDto::lastName).toList());
            assertThat(dto.username()).isIn(expectedDtoList.stream().map(UserRequestResponseDto::username).toList());
            assertThat(dto.email()).isIn(expectedDtoList.stream().map(UserRequestResponseDto::email).toList());
        });

        //verify
        verify(userRepository, times(1)).findAll();

    }

    @Test
    @DisplayName("When Valid Username And UserRequest Are Given Then User Is Updated And Returned")
    void whenValidUsernameAndUserRequestAreGiven_thenUserIsUpdatedAndReturned() {

        //give
        String username = "Test-Username";
        User user = DataGenerator.generateUser();
        UserRequestResponseDto userRequest = DataGenerator.generateUserRequestResponseDtoUpdate();

        //mock the calls
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            return invocation.<User>getArgument(0);
        });
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        //when
        UserRequestResponseDto updatedUserRequest = userService.updateUser(username, userRequest);

        // then
        assertThat(updatedUserRequest).isNotNull();
        assertThat(updatedUserRequest.firstName()).isEqualTo(userRequest.firstName());
        assertThat(updatedUserRequest.lastName()).isEqualTo(userRequest.lastName());
        assertThat(updatedUserRequest.email()).isEqualTo(userRequest.email());
        assertThat(updatedUserRequest.username()).isEqualTo(userRequest.username());

        //verify
        verify(userRepository, times(1)).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser.getFirstName()).isEqualTo(userRequest.firstName());
        assertThat(capturedUser.getLastName()).isEqualTo(userRequest.lastName());
        assertThat(capturedUser.getEmail()).isEqualTo(userRequest.email());
        assertThat(capturedUser.getUsername()).isEqualTo(userRequest.username());

    }

    @Test
    @DisplayName("When Text Is Given For Search Then List Of Matching Users Is Returned")
    void whenTextIsGivenForSearch_thenListOfMatchingUsersIsReturned() {

        //given
        String searchText = "test";
        User user1 = new User("user1", "First", "User", "user1@example.com");
        User user2 = new User("user2", "Second", "User", "user2@example.com");
        List<User> mockUsers = Arrays.asList(user1, user2);

        //mock the calls
        when(userRepository.searchUsers(anyString())).thenReturn(mockUsers);

        // mock userConverter behavior
        UserRequestResponseDto userDto1 = new UserRequestResponseDto("First", "User", "user1", "user1@example.com");
        UserRequestResponseDto userDto2 = new UserRequestResponseDto("Second", "User", "user2", "user2@example.com");
        when(userConverter.toUserRequestResponseDtoList(mockUsers))
                .thenReturn(Arrays.asList(userDto1, userDto2));

        // when
        List<UserRequestResponseDto> foundUsers = userService.searchUsers(searchText);

        // then
        assertThat(foundUsers).isNotNull();
        assertThat(foundUsers).hasSize(2);

        //verify
        verify(userRepository, times(1)).searchUsers(searchText);
        verify(userConverter, times(1)).toUserRequestResponseDtoList(mockUsers);

    }

}
