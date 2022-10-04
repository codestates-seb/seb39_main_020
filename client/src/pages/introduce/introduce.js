import { useEffect } from "react";
import { useOutletContext } from "react-router-dom";

const Introduce = () => {
    return (
        <div>
            <h2>React + MUI + Redux-Toolkit</h2>
            <p>
                <i>created by hmkim</i>
            </p>
            <hr/>
            <h3>[ 사용 라이브러리 ]</h3>
            <ul>
                <li>React</li>
                <ul>
                    <li>react@17.0.2 <i>by Create-react-app</i></li>
                    <li>react-router-dom@6.2.1</li>
                    <li>react-redux@7.2.6</li>
                    <li>redux-logger@3.0.6</li>
                    <li>axios@0.26.0</li>
                    <li>@reduxjs/toolkit@1.7.2</li>
                </ul>
                <li>MUI</li>
                <ul>
                    <li>@mui/material@5.4.2</li>
                    <li>@mui/icons-material@5.4.2</li>
                </ul>
            </ul>
            <h3>[ 프로젝트 기본 구성 ]</h3>
            <ul>
                <li>/node_modules : npm installed library</li>
                <li>/public : public resources (<i>images, css, static html..and so on</i>)</li>
                <li>/src : main folder</li>
                <ul>
                    <li>/apis : axios 함수 집합</li>
                    <li>/components : 공통 컴포넌트 선언용(로딩바, 페이징 등등)</li>
                    <li>/configs : 기본 설정파일 저장</li>
                        <ul>
                            <li>axiosConfig.js : axios공통 config선언 및 공통 config을 사용하는 axios wrapper함수 제공</li>
                            <li>rootRoutes.js : 화면 라우트용 메뉴관리 object. DB관리시에는 state로 전환하여 사용할 것</li>
                            <li>store.js : redux store선언 및 config제공</li>
                        </ul>
                    <li>/layout : 기본 layout 및 include용 화면 조각 제공 (<i>gnb, lnb, footer.. and so on</i>)</li>
                    <li>/pages : 각 화면용 page 파일. 현재는 단순한 화면이라 vm, v를 구분 하지 않으나 필요시 구분하여 사용할 것</li>
                    <li>/reducers : redux-toolkit slice(state and reducer, combineReducer) 와 createAsyncThunk(action with thunk)을 선언하여 사용. state당 하나를 사용하도록 구성함. action이 많아질 경우 action폴더 별도 구성 가능</li>

                </ul>
            </ul>
        </div>
    )
}

export default Introduce;