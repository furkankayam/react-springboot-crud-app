import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { deleteUser, getUsers, searchUsers } from "../redux/userSlice";
import { GrUpdate } from "react-icons/gr";
import { RiDeleteBin6Line } from "react-icons/ri";
import { IoIosAddCircleOutline, IoIosSearch } from "react-icons/io";
import { Link } from "react-router-dom";
import { useState } from "react";

const List = () => {
  const dispatch = useDispatch();
  const { users } = useSelector((store) => store.user);

  const [text, setText] = useState("");

  useEffect(() => {
    dispatch(getUsers());
  }, [dispatch]);

  const handleDeleteButton = (username) => {
    dispatch(deleteUser(username));
  };

  const handleInput = (e) => {
    setText(e.target.value);
  };

  const handleSearchButton = (e) => {
    e.preventDefault();
    dispatch(searchUsers(text));
  };

  return (
    <>
      <div className="relative overflow-x-auto shadow-md sm:rounded-lg w-[90%] mx-auto">
        <div className="text-white text-3xl flex justify-between mb-7">
          <form className="flex gap-2">
            <input
              className="bg-transparent border-2 border-[#374151] rounded-md text-[17px] placeholder:text-[17px] placeholder:indent-1"
              type="text"
              placeholder="Search..."
              value={text}
              onChange={handleInput}
            />
            <button
              onClick={handleSearchButton}
              className=" text-[#9CA3AF] text-[28px]"
            >
              <IoIosSearch />
            </button>
          </form>
          <Link to="/create-user">
            <IoIosAddCircleOutline className="text-blue-600" />
          </Link>
        </div>
        <table className="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
          <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
            <tr>
              <th scope="col" className="px-6 py-3">
                FIRST NAME
              </th>
              <th scope="col" className="px-6 py-3">
                LAST NAME
              </th>
              <th scope="col" className="px-6 py-3">
                USERNAME
              </th>
              <th scope="col" className="px-6 py-3">
                EMAIL
              </th>
              <th scope="col" className="px-6 py-3"></th>
            </tr>
          </thead>
          <tbody>
            {users.map((user, index) => (
              <tr
                key={index}
                className="odd:bg-white odd:dark:bg-gray-900 even:bg-gray-50 even:dark:bg-gray-800 border-b dark:border-gray-700"
              >
                <td className="px-6 py-4">{user.firstName}</td>
                <td className="px-6 py-4">{user.lastName}</td>
                <td className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                  {user.username}
                </td>
                <td className="pl-6 pr-0 py-4">{user.email}</td>
                <td className="pr-6 pl-0 py-4 flex justify-evenly">
                  <div className="m-0 p-0"></div>
                  <div className="flex gap-10 text-[18px]">
                    <Link to="/update-user">
                      <GrUpdate className="text-green-400" />
                    </Link>
                    <button onClick={() => handleDeleteButton(user.username)}>
                      <RiDeleteBin6Line className="text-red-500" />
                    </button>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <ul className="text-white"></ul>
    </>
  );
};

export default List;
