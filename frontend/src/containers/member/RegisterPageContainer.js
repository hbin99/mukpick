import { withRouter } from 'react-router-dom';
import RegisterForm from "../../components/member/Register";
import {shallowEqual, useDispatch, useSelector} from "react-redux";
import {chengeField, registerData, initializeForm,checkId} from "../../modules/member/findUser";
import {useEffect, useState} from "react";

const RegisterPageContainer = () => {
    const dispatch = useDispatch();
    const [vaildError,setVaildError] = useState(null);
    const { resultMsg, error, form,loading } = useSelector(({member,loading }) => ({
        form :member.register,
        resultMsg: member.resultMsg,
        error: member.error,
        loading: loading['/member/register'],
    }),shallowEqual
    );
    const onCheck = (e) => {
        if(form.userId === ''){
            setVaildError('아이디를 입력해주세요.');
            return;
        }
        dispatch(checkId( {userId : form.userId} ));
    };
    const onChange = (e) => {
        const {value,name} = e.target;
        if(vaildError){
            setVaildError(null);
        }
        dispatch(
            chengeField({
                form: 'register',
                key:name,
                value
            })
        );
    };
    const onblur = (e) => {
        const {value,name} = e.target;
        if(name === 'email'){
            const regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
            console.log('이메일 유효성 검사 :: ', regExp.test(e.target.value))
            if(!regExp.test(e.target.value)){
                setVaildError('이메일을 제대로 입력해주세요.');
                return;
            }
        }
        dispatch(
            chengeField({
                form: 'register',
                key:name,
                value
            })
        );
    };
    const onRegister = (e) => {
        if(form.userId === ''){
            setVaildError('아이디를 입력해주세요.');
            return;
        }
        if(form.userName === ''){
            setVaildError('이름을 입력해주세요.');
            return;
        }
        if(form.userPassword === '' || form.userPasswordConfirm === ''){
            setVaildError('비밀번호를 입력해주세요.');
            return;
        }
        if(form.userPasswordConfirm !== form.userPassword){
            setVaildError('비밀번호가 다릅니다. 다시 입력 해주세요.');
            return;
        }
        if(form.email === ''){
            setVaildError('이메일을 입력해주세요.');
            return;
        }
        if(form.phone === ''){
            setVaildError('핸드폰을 입력해주세요.');
            return;
        }
        dispatch(registerData({
            userId : form.userId
            ,userName :  form.userName
            ,userPassword :  form.userPassword
            ,email :  form.email
            ,phone :  form.phone
            ,gender :  form.gender
            ,age :  form.age
        }));
    };
    useEffect(()=>{
        dispatch(initializeForm('register'));
    },[dispatch]);
  return (
    <>
      <RegisterForm
          type = "register"
          form={form}
          loading={loading}
          error={error}
          error={vaildError}
          resultText={resultMsg}
          onClick={onRegister}
          onChange={onChange}
          onCheck={onCheck}
          onblur={onblur}
      />
    </>
  );
};

export default withRouter(RegisterPageContainer);
