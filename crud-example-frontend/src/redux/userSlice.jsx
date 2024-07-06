import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const initialState = {
  users: [],
};

export const getUsers = createAsyncThunk("getUsers", async () => {
  const response = await axios.get("http://localhost:8080/api/users");
  return response.data;
});

export const createUserSlice = createAsyncThunk("createUser", async (user) => {
  const response = await axios.post(`http://localhost:8080/api/users`, user, {
    headers: {
      "Content-Type": "application/json",
    },
  });
  return response.data;
});

export const searchUsers = createAsyncThunk("searchUsers", async (text) => {
  const response = await axios.get(
    `http://localhost:8080/api/users/search?text=${text}`,
  );
  return response.data;
})

export const updateUserSlice = createAsyncThunk("updateUser", async (user) => {
  const response = await axios.post(
    `http://localhost:8080/api/users/update/${user.username}`,
    user,
    {
      headers: {
        "Content-Type": "application/json",
      },
    }
  );
  return response.data;
});

export const deleteUser = createAsyncThunk("deleteUser", async (username) => {
  const response = await axios.delete(
    `http://localhost:8080/api/users/delete/${username}`
  );
  return response.data;
});

export const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(getUsers.fulfilled, (state, action) => {
        state.users = action.payload;
      })
      .addCase(searchUsers.fulfilled, (state, action) => {
        state.users = action.payload;
      })
      .addCase(createUserSlice.fulfilled, (state, action) => {
        state.users = [...state.users, action.payload];
      })
      .addCase(updateUserSlice.fulfilled, (state, action) => {
        const updatedUser = action.payload;
        console.log("updatedUser", updatedUser);
        state.users = state.users.map((user) =>
          user.username === updatedUser.username ? updatedUser : user
        );
      })
      .addCase(deleteUser.fulfilled, (state, action) => {
        state.users = state.users.filter(
          (user) => user.username !== action.payload.username
        );
      });
  },
});

export const {} = userSlice.actions;
export default userSlice.reducer;
