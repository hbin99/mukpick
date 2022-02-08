import { withRouter } from 'react-router-dom';
import LoginBar from '../../components/member/LoginBar';
import {useDispatch, useSelector} from "react-redux";
import {useEffect, useState} from "react";
import {chengeField, login} from "../../modules/member/login";
import {checkId} from "../../modules/member/findUser";
const LoginPageContainer = ({history}) => {
    const dispatch = useDispatch();
    const [vaildError,setVaildError] = useState(null);
    const { resultMsg, error, form,loading, user } = useSelector(({member,loading, user }) => ({
            form :user,
            resultMsg: member.resultMsg,
            error: member.error,
            loading: loading['/member/login' ],
            user :user.userId ,
        }),
    );
    const onClick = (e) => {
        if(form.userId === ''){
            setVaildError('아이디를 입력해주세요.');
            return;
        }
        if(form.userPassword === ''){
            setVaildError('비밀번호를 입력해주세요.');
            return;
        }
        dispatch(login( {userId : form.userId , userPassword : form.userPassword} ));
    }
    const onChange = (e) => {
        const {value,name} = e.target;
        if(vaildError){
            setVaildError(null);
        }
        dispatch(
            chengeField({
                form: 'login',
                key:name,
                value
            })
        );
    }
    useEffect(()=>{
        //dispatch(initializeLoginForm('login'));
        if(user){
            //history.push('/');
            try{
                localStorage.setItem('user',JSON.stringify(user));
            }catch (e){
                console.log('localStorage is not working');
            }
        }
    },[dispatch, history,user ]);
  return (
    <>
          <LoginBar
            onClick = {onClick}
            onChange = {onChange}
            form = {form}
            loading={loading}
            error = {error}
            error = {vaildError}
          />
    </>
  );
};

export default withRouter(LoginPageContainer);
