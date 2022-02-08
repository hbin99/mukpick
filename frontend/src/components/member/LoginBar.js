import styled from 'styled-components';
import Button from "../common/Button";
import React from "react";

const ErrorMsg = styled.div`
    color :red;
    margin-top : 1rem;
`
const Input = styled.input`
    outline: none;
    border-radius: 0px;
    line-height: 2.5rem;
    font-size: 1.2rem;
    padding-left: 0.5rem;
    padding-right: 0.5rem;
`;
const SearchTextItem = ( {item} ) => {
    const { RESULT_MSG, RESULT_FLAG } = item;
    return (
        <div>
            { RESULT_FLAG === 'SUCCESS' ?
                (
                    <div>{RESULT_MSG} </div>
                ):(
                    <ErrorMsg>{RESULT_MSG}</ErrorMsg>
                )
            }
        </div>
    );
};
const LoginBar = ({onClick, onChange, form, error}) => {
  return (
    <>
        <Input placeholder='아이디'
               name = "userId"
               onChange={onChange}
               value={form.userId}
        />
        <Input placeholder='비밀번호'
               name = "userPassword"
               onChange={onChange}
               type ="password"
               value={form.userPassword}
        />
        <Button onClick={onClick}>로그인</Button>
        <Button to="/find-id">아이디찾기</Button>
        <Button to="/find-password">비밀번호 찾기</Button>
        {error && <ErrorMsg>{error}</ErrorMsg> }
        { !error && form.resultMsg &&<SearchTextItem item={form.resultMsg} />}
    </>
  );
};

export default LoginBar;
