import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const SideBarBlock = styled.div`
  display: flex;
  z-index: 1;
  left: 0;
  width: 18%;
  float: left;
  flex-direction: column;
  height: 100vh;
  background: #e3855b;
  padding: 0;
  overflow: hidden;
  box-shadow: 2px 0 4px #cfcfcf;
`;

const HomeBtn = styled.div`
  height: 4rem;
  width: 4rem;
  margin: 0px auto;
  background: #cda8c7;
`;

const SideButton = styled(Link)`
  outline: none;
  display: block;
  text-decoration: none;
  text-align: center;
  margin-top: 10px;
  color: black;
`;

const AdminSideNav = () => {
  return (
    <>
      <SideBarBlock>
        <HomeBtn>
          <Link to="/" className="logo" style={{ color: 'black' }}>
            먹픽
          </Link>
        </HomeBtn>
        <SideButton to="/admin">관리자 메인</SideButton>
        <SideButton to="/admin/search-mnt">검색 관리</SideButton>
        <SideButton to="/admin/food-mnt">음식 관리</SideButton>
        <SideButton to="/">사용자 모드로 이동</SideButton>
      </SideBarBlock>
    </>
  );
};

export default AdminSideNav;
