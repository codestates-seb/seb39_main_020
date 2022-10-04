/* eslint-disable */
import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Login() {
  const navigate = useNavigate();

  // 헤더정보 세팅
  const _makeHeaders = function (accessToken, refreshToken) {
    const config = {
      headers: {},
    };

    // access token 헤더 설정
    if (accessToken) {
      config.headers["Authorization"] = `Bearer ${accessToken}`;
    }

    // 그외 Header 설정
    config.headers["Content-type"] = "application/json";
    config.headers["Accept"] = "application/json";

    return config;
  };

  function greetUser() {
    const body = {
      email: Email,
      password: PW,
    };

    // accesstoken 이 있다고 가정하고
    const accessToken = "a9ace025c90c0da2161075da6ddd3492a2fca776";
    const refreshToken = "98e26535dd9f0730a31c";

    axios
      .post(
        "http://localhost:8080/login",
        body,
        _makeHeaders(accessToken, refreshToken)
      )
      .then((response) => {
        console.log(response.data);
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
