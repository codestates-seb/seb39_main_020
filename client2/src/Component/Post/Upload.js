import React, { useState } from 'react'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Upload(props) {
  const [Title, setTitle] = useState("");
  const [Content, setContent] = useState("");
  
  let navigate = useNavigate();
//11번째줄 주석 삭제금지 -> eslint에러 처리를 위해 씀
    const onSubmit = (e) => {//eslint-disable-line no-unused-vars
      e.preventDefault();//onSubmit해도 새로고침발생안됨. 초기화방지

      if(Title === "" || Content === "") {
        return alert("모든 항목을 채워주세요!")
      }
      



      let body = {
        title: Title,
        content: Content,
      };

      axios.post("/upload",
      body, {headers: {'Content-type': 'application/json', 'Accept': 'application/json'}})
      .then((response) => {
        if(response.data.success) {
          alert("글 작성이 완료되었습니다.");
          navigate("/list");
        } else {
          alert("글 작성에 실패하였습니다.");
        }
      })
      .catch((err) => {
        console.log(err);
      })

    

  return (
    <div>
      <label>제목</label>
      <input
        id="title"
        type="text"
        value={Title}
        onChange={(e) => {
          setTitle(e.currentTarget.value);
        }}
        />

        <label>내용</label>
        <input
        id="content"
        value={Content}
        onChange={(e) => {
          setContent(e.currentTarget.value);
        }}
      />
      <button 
        onClick={(e) => {
          onSubmit(e);
        }}
        >
          제출
        </button>
    </div>
  );
}}

export default Upload;