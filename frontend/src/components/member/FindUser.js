import React from "react";
import styled from "styled-components";
import Button from "../common/Button";

const ErrorMsg = styled.div`
    color :red;
    margin-top : 1rem;
`
const ResultMsg = styled.div`
    color :blue;
    margin-top : 1rem;
`

const Input = styled.input`
    width: 30%;
    border: 1px solid #0333;
    outline: none;
    border-radius: 0px;
    line-height: 2.5rem;
    font-size: 1.2rem;
    padding-left: 0.5rem;
    padding-right: 0.5rem;
    margin : 5px;
`;
const textMap = {
  //  login : '로그인',
    findId : '아이디 찾기',
    findPassword : '비밀번호 찾기',
}

const SearchTextItem = ({ item }) => {
  const { RESULT_MSG, RESULT_FLAG } = item;
  return (
    <div>
        { RESULT_FLAG === 'SUCCESS' ?
        (
            <ResultMsg>{RESULT_MSG}</ResultMsg>
        ):(
            <div>{RESULT_MSG} </div>
        )}
    </div>
  );
};

const FindUser = ({ type, loading,form, error,resultText , onChange, onClick }) => {
    const text = textMap[type];
  return (
    <>
        <h3>{text}</h3>
        {type === 'findId' ? (
            <div>
                <label>회원이름:</label>
                <Input placeholder={'이름'}
                       name = "userName"
                       onChange={onChange}
                       value={form.userName}
                />
            </div>
        ) : (
            <div>
                <label> 아이디:</label>
                <Input placeholder={'아이디'}
                       name = "userId"
                       onChange={onChange}
                       value={form.userId}
                />
            </div>
        )}

        <div>
            <label>핸드폰:</label>
            <Input placeholder={'핸드폰 번호'}
                   name = "phone"
                   type ={type}
                   onChange={onChange}
                   value={form.phone}
            />
        </div>
        <div>
            <label>이메일:</label>
            <Input placeholder={'이메일'}
                   name = "email"
                   type ={type}
                   onChange={onChange}
                   value={form.email}
            />
        </div>
        <Button onClick={onClick} type ={type} >찾기</Button>
        {error && <ErrorMsg>{error}</ErrorMsg> }
      {!loading && !error &&resultText && <SearchTextItem item={resultText} />}
    </>
  );
};
export default FindUser;
