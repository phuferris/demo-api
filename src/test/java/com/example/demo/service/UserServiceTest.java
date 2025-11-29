package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {
    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private UserService userService = new UserService(userRepository);

    @Test
    public void testGetUserById() {
        int userId = 100;
        User mockUser = buildMockUser(userId);
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockUser));
        Optional<User> user = userService.findById((long)userId);
        Mockito.verify(userRepository).findById((long)userId);
        assertEquals(
                user.get().getEmail(),
                mockUser.getEmail(),
                "Return user email should match mock user email"
        );
    }

    @Test
    public void testFindUserById() {
        User mockUser = new User("Phu Nguyen", "pnguyen@gmail.com");
        mockUser.setId(123L);
        Mockito.when(userRepository.findById(123L))
                .thenReturn(Optional.of(mockUser));

        Optional<User> user = userService.findById(123L);

        Mockito.verify(userRepository).findById(123L);
        assertEquals(mockUser.getEmail(), user.get().getEmail());
    }

    @Test
    public void testFindAllChildrenByUserIdAndAgeRange() {
        long userId = 201;
        long parentUserId = 100;
        User mockUser = buildMockUser(userId);
        Mockito.when(
                userRepository.findByParentAndDateOfBirthBetween(Mockito.any(), Mockito.any(), Mockito.any())
        ).thenReturn(List.of(mockUser));
        Mockito.when(
                userRepository.getReferenceById(Mockito.any())
        ).thenReturn(mockUser);
        List<User> users = userService.findAllChildrenByUserIdAndAgeRange(
                parentUserId,
                0,
                5
        );
        Mockito.verify(userRepository).findByParentAndDateOfBirthBetween(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.verify(userRepository).getReferenceById(parentUserId);
        assertEquals(1, users.size());
    }

    private User buildMockUser(long userId) {
        User mockUser = new User("Phu Nguyen", "pnguyen@gmail.com");
        mockUser.setId(userId);
        mockUser.setDateOfBirth(LocalDate.of(1980, 10, 10));
        return mockUser;
    }
}
