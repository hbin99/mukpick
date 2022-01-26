import React from 'react';
import styled from 'styled-components';
import '../../lib/css/admin.css';

const AdminPanelBlock = styled.div`
  display: flex;
  margin-left: 200px;
  flex-direction: column;
  flex: auto;
  position: relative;
  /* 브라우저 크기에 따라 가로 크기 변경 */
  @media (max-width: 1024px) {
    width: 768px;
  }

  @media (max-width: 768px) {
    width: 100%;
  }
`;

const AdminPanel = ({ children, ...rest }) => {
  // style, className, onClick, onMouseMove 등의 props를 사용할 수 있도록
  // ...rest를 사용하여 ResponsiveBlock에게 전달
  return <AdminPanelBlock {...rest}>{children}</AdminPanelBlock>;
};

export default AdminPanel;
