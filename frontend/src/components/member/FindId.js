import React from "react";
import styled from "styled-components";
import Button from "../common/Button";

const Input = styled.input`
    outline: none;
    border-radius: 0px;
    line-height: 2.5rem;
    font-size: 1.2rem;
    padding-left: 0.5rem;
    padding-right: 0.5rem;
`;

const textMap = {
    login : '로그인',
    userId : '아이디 찾기',
}

const SearchTextItem = ({ item }) => {
  const { RESULT_MSG, RESULT_FLAG } = item;
  return (
    <div>
      <div>{RESULT_MSG} </div>
      <div>{RESULT_FLAG} </div>
    </div>
  );
};



const FindId = ({ type, loading,form, resultText , onChange, onClick }) => {
    //console.log({form});
    const text = textMap[type];
  return (
    <>
        <h3>{text}</h3>
        <div>
            <label>회원이름:</label>
            <Input placeholder={'이름'}
                   name = "userName"
                   onChange={onChange}
                   value={form.userName}
            />
        </div>
        <div>
            <label>핸드폰:</label>
            <Input placeholder={'핸드폰 번호'}
                   name = "phone"
                   onChange={onChange}
                   value={form.phone}
            />
        </div>
        <div>
            <label>이메일:</label>
            <Input placeholder={'이메일'}
                   name = "email"
                   onChange={onChange}
                   value={form.email}
            />
        </div>
        <Button onClick={onClick} >찾기</Button>
      {!loading && resultText && <SearchTextItem item={resultText} />}
    </>
  );
};
export default FindId;
