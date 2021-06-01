import React, { Component } from 'react';
import Container from './Container';
import Footer from './Footer';
import { getAllStudents } from './client';
import { Table, Avatar, Spin, Modal, Empty } from 'antd';
import { LoadingOutlined } from '@ant-design/icons';
import AddStudentForm from './forms/AddStudentForm';
import { errorNotification } from './Notification';

const getIndicatorIcon = () => <LoadingOutlined style={{ fontSize: 24 }} spin />;

class App extends Component {

  state = {
    students: [],
    isFetching: false,
    isAddStudentModalVisible: false
  };

  componentDidMount() {
    this.fetchStudents();
  }

  openAddStudentModal = () => this.setState({ isAddStudentModalVisible: true });
  closeAddStudentModal = () => this.setState({ isAddStudentModalVisible: false });

  fetchStudents = () => {
    this.setState({
      isFetching: true
    });
    getAllStudents().then(res => res.json().then(students => {
      this.setState({
        students,
        isFetching: false
      });
    })).catch(err => {
      console.log(err);
      errorNotification(err.message, err.httpStatus);
      this.setState({ isFetching: false });
    });
  };

  render() {
    const { students, isFetching, isAddStudentModalVisible } = this.state;
    const commonElements = () => (
      <div>
        <Modal title='Add new student' visible={isAddStudentModalVisible}
          onOk={this.closeAddStudentModal} onCancel={this.closeAddStudentModal} width={1000}>
          <AddStudentForm onSuccess={() => { this.closeAddStudentModal(); this.fetchStudents(); }}
            onFailure={(err) => errorNotification(err.message, err.httpStatus)} />
        </Modal>
        <Footer numberOfStudents={students.length} handleAddStudentClickEvent={this.openAddStudentModal} />
      </div>
    );

    if (isFetching) {
      return (
        <Container>
          <Spin indicator={getIndicatorIcon()}></Spin>
        </Container>
      );
    }
    if (students && students.length) {

      const columns = [
        {
          title: '',
          key: 'avatar',
          render: (text, student) => (
            <Avatar size='large'>
              {`${student.firstName.charAt(0).toUpperCase()}${student.lastName.charAt(0).toUpperCase()}`}
            </Avatar>
          )
        },
        {
          title: 'Student Id',
          dataIndex: 'studentId',
          key: 'studentId'
        },
        {
          title: 'First Name',
          dataIndex: 'firstName',
          key: 'firstName'
        },
        {
          title: 'Last Name',
          dataIndex: 'lastName',
          key: 'lastName'
        },
        {
          title: 'Email',
          dataIndex: 'email',
          key: 'email'
        },
        {
          title: 'Gender',
          dataIndex: 'gender',
          key: 'gender'
        }
      ];

      return (
        <Container>
          <Table dataSource={students} columns={columns} pagination={false} rowKey='studentId' />
          { commonElements()}
        </Container>
      );

      // return students.map((student, index) => {
      //   return (
      //     <div key={index}>
      //       <h2>{student.studentId}</h2>
      //       <p>{student.firstName}</p>
      //       <p>{student.lastName}</p>
      //       <p>{student.email}</p>
      //       <p>{student.gender}</p>
      //     </div>
      //   );
      // });
    }
    return (
      <Container>
        <Empty image="https://gw.alipayobjects.com/zos/antfincdn/ZHrcdLPrvN/empty.svg" description={<h1>No students found.</h1>} />
        { commonElements()}
      </Container>
    );
  }
}

export default App;