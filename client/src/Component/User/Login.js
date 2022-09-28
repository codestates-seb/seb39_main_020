/* eslint-disable */
import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Login() {
  const navigate = useNavigate();

  function greetUser() {
    const body = {
      email: Email,
      password: PW,
    };
    console.log("a");
    console.log(Email);
    console.log(PW);

    axios
      .post("http://localhost:8080/login", body, {
        headers: {
          "Content-type": "application/json",
          Accept: "application/json",
        },
      })
      .then((response) => {
        console.log(response.data);
        console.log("success!");
        navigate("/");
      })
      .catch((error) => {
        console.log(error);
      });
  }
  const [Email, setEmail] = useState("");
  const [PW, setPW] = useState("");

  // useEffect (() => {

  // }, []);

  return (
    <div>
      <label>이메일</label>
      <input
        type="email"
        value={Email}
        onChange={(e) => setEmail(e.currentTarget.value)}
      />
      <label>비밀번호</label>
      <input
        type="password"
        value={PW}
        onChange={(e) => setPW(e.currentTarget.value)}
      />
      <button onClick={greetUser}>로그인</button>
    </div>
  );
}

export default Login;
