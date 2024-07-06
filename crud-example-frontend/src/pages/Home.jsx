import Header from "../components/Header";
import Footer from "../components/Footer";
import List from "../components/List";
import React from "react";
import { Router, Route, Link, Routes, BrowserRouter } from "react-router-dom";
import Create from "./Create";
import Update from "./Update";

const Home = () => {
  return (
    <>
      <Header />
      <BrowserRouter>
        <Routes>
          <Route path="/create-user" element={<Create />} />
          <Route path="/update-user" element={<Update />} />
          <Route path="/" element={<List />} />
        </Routes>
      </BrowserRouter>
      <Footer />
    </>
  );
};

export default Home;