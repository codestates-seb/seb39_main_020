/* eslint-disable */
import React, {useState, useEffect} from 'react'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Register() {

    const [NickName, setNickName] = useState("");
    const [Email, setEmail] = useState("");
    const [PW, setPW] = useState("");
    const [PWConfirm, setPWConfirm] = useState("");

    const navigate = useNavigate();


      const body = {
        nickname: "",
        email: "",
        password: "",
      }

      useEffect(()=> {
        axios.post('/register', 
        body, {headers: {'Content-type': 'application/json', 'Accept': 'application/json'}})
        .then((response) => {
          console.log(response.data)
          console.log('success!')
          navigate('/login')
        })
        .catch((error) => {
          console.log(error)
        })

      }, []);



  return (
    <div>
        <form>
            <label>이름</label>
            <input
            type="nickname"
            value={NickName}
            onChange={(e) => setNickName(e.currentTarget.value)}
            />
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
            <label>비밀번호 확인</label>
            <input
            type="password"
            value={PWConfirm}
            onChange={(e) => setPWConfirm(e.currentTarget.value)}
            />
          <button onClick={(e) => Register(e)}>
          회원가입
        </button>
        </form>
        </div>
    
  )
}

export default Register;