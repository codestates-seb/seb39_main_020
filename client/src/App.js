import logo from "./logo.svg";
import "./App.css";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import React, { useState, Fragment } from "react";

import Heading from "./Component/Heading";
import Institutes from "./Component/Post/Institutes";
import List from "./Component/Post/List";
import Upload from "./Component/Post/Upload";
import Community from "./Component/Post/Community";
import Qna from "./Component/Post/Qna";

import Login from "./Component/User/Login";
import Register from "./Component/User/Register";

const Home = () => {
  return <div>Home</div>;
};

function App() {
  const [ContentList, setContentList] = useState([]);

  return (
    <Router>
      <Heading />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route
          path="/institutes"
          element={
            <Institutes
              ContentList={ContentList}
              setContentList={setContentList}
            />
          }
        />

        <Route
          path="/upload"
          element={
            <Upload ContentList={ContentList} setContentList={setContentList} />
          }
        />

        <Route
          path="/list"
          element={
            <List ContentList={ContentList} setContentList={setContentList} />
          }
        />

        <Route
          path="/community"
          element={
            <Community
              ContentList={ContentList}
              setContentList={setContentList}
            />
          }
        />

        <Route
          path="/qna"
          element={
            <Qna ContentList={ContentList} setContentList={setContentList} />
          }
        />

        <Route path="/login" element={<Login />} />

        <Route path="/register" element={<Register />} />
      </Routes>
    </Router>
  );
}

export default App;
