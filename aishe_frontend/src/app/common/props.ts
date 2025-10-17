export const props = {
  alphaNumeric:
    'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789',
  invalidCaptchaMsg: 'Invalid captcha',
  passwordLogic:
    'The password must contain 8 -16 characters. It should also have one upper case letter. one number and one special character. Valid characters are letters (a-z, A-Z). numbers (0-9) and #, @, &, $, =, ~, %, *. Spaces are not permitted. The password is case sensitive.',
  reportsData: [
    {
      title: 'Basic Reports',
      reports: [
        {
          id: 1,
          name: 'University Report',
          reportNumber: 'Report 1',
          showState:false
        },
        {
          id: 2,
          name: 'College Report',
          reportNumber: 'Report 2',
          showState:false
        },
        {
          id: 3,
          name: 'Standalone Institution Report',
          reportNumber: 'Report 3',
          showState:false
        },
      ],
    },
    {
      title: 'Number Of Institutions',
      reports: [
        {
          id: 1,
          name: 'State-Wise Number of Institutions',
          reportNumber: 'Report 6',
          showState:false
        },
        {
          id: 2,
          name: 'State-Wise Number of Institutions - Urban',
          reportNumber: 'Report 7',
          showState:false
        },
        {
          id: 3,
          name: 'State-Wise Number of Institutions - Rural',
          reportNumber: 'Report 8',
          showState:false
        },
        {
          id: 4,
          name: 'State & Specialisation - Wise Number Of Institutions',
          reportNumber: 'Report 9',
          showState:false
        },
        {
          id: 5,
          name: 'State-Wise Number Of Colleges By Recognition',
          reportNumber: 'Report 10',
          showState:false
        },
        {
          id: 6,
          name: 'Type-Wise Number Of Institutions Attached With University',
          reportNumber: 'Report 12',
          showState:false
        },
        {
          id: 7,
          name:
            'Management-Wise Number Of Institutions Attached With University',
          reportNumber: 'Report 13',
          showState:false
        },
        {
          id: 8,
          name:
            'State-Wise Number Of University Offering Education Through Distance Mode',
          reportNumber: 'Report 14',
          showState:false
        },
      ],
    },

    {
      title: 'Teaching Staff',
      reports1: [
        {
          subTitle: 'State-wise',
          subreports: [
            {
              id: 1,
              name: 'State & Post & Gender-Wise Number of Teacher',
              reportNumber: 'Report 15',
              showState:false
            },
            {
              id: 2,
              name: 'State & Category & Gender-Wise Number of Teacher',
              reportNumber: 'Report 16',
              showState:false
            },
            {
              id: 3,
              name: 'State-Wise Sanctioned Strength And Teaching Staff In Position',
              reportNumber: 'Report 138',
              showState:false
            },
          ],
        },
        {
          subTitle: 'District-wise',
          subreports: [
            {
              id: 1,
              name: 'District & Category & Gender-Wise Teacher - ALL',
              reportNumber: 'Report 116',
              showState:false
            },
            {
              id: 2,
              name: 'District & Category & Gender-Wise Teacher - University',
              reportNumber: 'Report 116A',
              showState:false
            },
            {
              id: 3,
              name: 'District & Category & Gender-Wise Teacher - College',
              reportNumber: 'Report 116B',
              showState:false
            },
            {
              id: 4,
              name:
                'District & Category & Gender-Wise Teacher - Standalone Institutions',
              reportNumber: 'Report 116C',
              showState:false
            },
          ],
        },
        {
          subTitle: 'Institution-wise',
          subreports: [
            {
              id: 1,
              name: 'Post-Wise Number Of Male & Female Teacher In University & Its Colleges',
              reportNumber: 'Report 17',
              showState:false
            },
            {
              id: 2,
              name: 'Category-Wise Number Of Male & Female Teacher In University & Its Colleges',
              reportNumber: 'Report 18',
              showState:false
            },
            {
              id: 3,
              name: 'Post & Gender-Wise Number of Teacher In Institution',
              reportNumber: 'Report 19',
              showState:false
            },
            {
              id: 4,
              name: 'Category & Gender-Wise Number of Teacher In Institution',
              reportNumber: 'Report 20',
              showState:false
            },
            {
               id:5,
               name: 'Post-Wise number Of Male & Female Teacher In Various Types Of Universities & Its Colleges',
               reportNumber:'Report 21',
               showState:false
            },
            {
              id: 6,
              name: 'Category-Wise Number Of Male & Female Teacher In Various Types Of Universities & Its Colleges',
              reportNumber: 'Report 22',
              showState:false
            },
            {
              id: 7,
              name: 'Faculty & Department-Wise Number Of Male & Female Teacher In Each Institution',
              reportNumber: 'Report 22A',
              showState:false
            },
            {
              id: 8,
              name: 'Post-Wise Sanctioned Strength And Teaching Staff In Position',
              reportNumber: 'Report 137',
              showState:false
            },
            {
              id: 9,
              name: 'Country and Post-Wise Number of Teachers',
              reportNumber: 'Report 165',
              showState:false
            },
            {
              id: 10,
              name: 'Country and Post-Wise Number of Teachers',
              reportNumber: 'Report 166',
              showState:false
            },
          ],
        },
      ],
    },
    {
      title: 'Non Teaching Staff',
      reports1: [
        {
          subTitle: 'State-wise',
          subreports: [
            {
              id: 1,
              name: 'State & Post & Gender-Wise Number of Non-Teaching Staff',
              reportNumber: 'Report 23',
              showState:false
            },
            {
              id: 2,
              name: 'State & Category & Gender-Wise Number of Non-Teaching Staff',
              reportNumber: 'Report 24',
              showState:false
            },
            {
              id: 3,
              name: 'State-Wise Sanctioned Strength And NON-Teaching Staff In Position',
              reportNumber: 'Report 136',
              showState:false
            },
          ],
        },
        {
          subTitle: 'District-wise',
          subreports: [
            {
              id: 1,
              name: 'District & Category & Gender-Wise Staff - ALL',
              reportNumber: 'Report 117',
              showState:false
            },
            {
              id: 2,
              name: 'District & Category & Gender-Wise Staff - University',
              reportNumber: 'Report 117A',
              showState:false
            },
            {
              id: 3,
              name: 'District & Category & Gender-Wise Staff - College',
              reportNumber: 'Report 117B',
              showState:false
            },
            {
              id: 4,
              name:
                'District & Category & Gender-Wise Standalone Institutions',
              reportNumber: 'Report 117C',
              showState:false
            },
          ],
        },
        {
          subTitle: 'Institution-wise',
          subreports: [
            {
              id: 1,
              name: 'Post-Wise Number Of Male & Female Non-Teaching Staff In University & Its Colleges',
              reportNumber: 'Report 25',
              showState:false
            },
            {
              id: 2,
              name: 'Category-Wise Number Of Male & Female Non-Teaching Staff In University & Its Colleges',
              reportNumber: 'Report 26',
              showState:false
            },
            {
              id: 3,
              name: 'Institution & Post & Gender-Wise Number of Non-Teaching Staff',
              reportNumber: 'Report 27',
              showState:false
            },
            {
              id: 4,
              name: 'Institution & Category & Gender-Wise Number of Non-Teaching Staff',
              reportNumber: 'Report 28',
              showState:false
            },
            {
              id: 5,
              name: 'Post-Wise number Of Male & Female Non-Teaching Staff In Various Types Of Universities & Its Colleges',
              reportNumber: 'Report 29',
              showState:false
            },
            {
              id: 6,
              name: 'Category-Wise Number Of Male & Female Non-Teaching Staff In Various Types Of Universities & Its Colleges',
              reportNumber: 'Report 30',
              showState:false
            },
            {
              id: 7,
              name: 'Post-Wise Sanctioned Strength And NON-Teaching Staff In Position',
              reportNumber: 'Report 135',
              showState:false
            },
          ],
        },
      ],
    },
    {
      title: 'Student Enrolment',
      reports1: [
        {
          subTitle: 'State-wise',
          subreports: [
            {
              id: 1,
              name: 'State & Level & Gender-Wise Student Enrolment',
              reportNumber: 'Report 31',
              showState:false
            },
            {
              id: 2,
              name: 'State & Level-Wise & Gender-Wise Student Enrolment Through Distance Mode Of Education',
              reportNumber: 'Report 32',
              showState:false
            },
            {
              id: 3,
              name: 'State-Wise & Gender-Wise Student Enrolment In Regional Centers Of University Offering Education Through Distance Mode',
              reportNumber: 'Report 32A',
              showState:false
            },
            {
              id: 4,
              name: 'State & Category & Gender-Wise Student Enrolment In Regional Centers Of University Offering Education Through Distance Mode',
              reportNumber: 'Report 32B',
              showState:false
            },
            {
              id: 5,
              name: 'State & Level-Wise & Gender-Wise Student On Roll In Distance Mode Of Education',
              reportNumber: 'Report 33',
              showState:false
            },
            {
              id: 6,
              name: 'State & Level-Wise & Gender-Wise Student Enrolment In Self-Financing Mode',
              reportNumber: 'Report 34',
              showState:false
            },
            {
              id: 7,
              name: 'State & Management & Gender-Wise Student Enrolment In Universities',
              reportNumber: 'Report 35A',
              showState:false
            },
            {
              id: 8,
              name: 'State & Management & Gender-Wise Student Enrolment In Colleges',
              reportNumber: 'Report 35B',
              showState:false
            },
            {
              id: 9,
              name: 'State & Management & Gender-Wise Student Enrolment In Standalone Institutions',
              reportNumber: 'Report 35C',
              showState:false
            },
            {
              id: 10,
              name: 'State & Category & Gender-Wise Student Enrolment',
              reportNumber: 'Report 39',
              showState:false
            },
            {
              id: 11,
              name: 'State & Level-Wise Intake And Student Enrolment In 1st Year',
              reportNumber: 'Report 43',
              showState:false
            },
            {
              id: 11,
              name: 'Country & Level & Gender-Wise Foreign Student Enrolment',
              reportNumber: 'Report 169',
              showState:false
            },
            {
              id: 12,
              name: 'State & Category & Gender-Wise Student Enrolment',
              reportNumber: 'Report 170',
              showState:false
            },
          ],
        },
        {
          subTitle: 'District-wise',
          subreports: [
            {
              id: 1,
              name: 'District & Level-Wise & Gender-Wise Student Enrolment - ALL',
              reportNumber: 'Report 107',
              showState:false
            },
            {
              id: 2,
              name: 'District & Level-Wise & Gender-Wise Student Enrolment - University',
              reportNumber: 'Report 107A',
              showState:false
            },
            {
              id: 3,
              name: 'District & Level-Wise & Gender-Wise Student Enrolment - College',
              reportNumber: 'Report 107B',
              showState:false
            },
            {
              id: 4,
              name:
                'District & Level-Wise & Gender-Wise Student Enrolment - Standalone Institutions',
              reportNumber: 'Report 107C',
              showState:false
            },
            {
              id: 5,
              name:
                'District & Category-Wise & Gender-Wise Student Enrolment - ALL',
              reportNumber: 'Report 108',
              showState:false
            },
            {
              id: 6,
              name:
                'District & Category-Wise & Gender-Wise Student Enrolment - University',
              reportNumber: 'Report 108A',
              showState:false
            },
            {
              id: 7,
              name:
                'District & Category-Wise & Gender-Wisee Student Enrolment - College',
              reportNumber: 'Report 108B',
              showState:false
            },
            {
              id: 8,
              name:
                'District & Category-Wise & Gender-Wise Student Enrolment - Standalone Institutions',
              reportNumber: 'Report 108C',
              showState:false
            },

          ],
        },
        {
          subTitle: 'Institution-wise',
          subreports: [
            {
              id: 1,
              name: 'Level-Wise & Gender-Wise Student Enrolment In University & Its Colleges',
              reportNumber: 'Report 36',
              showState:false
            },
            {
              id: 2,
              name: 'Level-Wise & Gender-Wise Student Enrolment In Various Types Of University & Its Colleges',
              reportNumber: 'Report 37',
              showState:false
            },
            {
              id: 3,
              name: 'Institution & LeveL & Gender-Wise Student Enrolment',
              reportNumber: 'Report 38',
              showState:false
            },
            {
              id: 4,
              name: 'Category-Wise & Gender-Wise Student Enrolment In University & Its Colleges',
              reportNumber: 'Report 40',
              showState:false
            },
            {
              id: 5,
              name: 'Category-Wise & Gender-Wise Student Enrolment In Various Types Of Universities',
              reportNumber: 'Report 41',
              showState:false
            },
            {
              id: 6,
              name: 'Institution & Category & Gender-Wise Student Enrolment',
              reportNumber: 'Report 42',
              showState:false
            },
            // {
            //   id: 7,
            //   name: 'Student Enrolment In University Teaching Departments & Its attached Colleges by type',
            //   reportNumber: 'Report 115',
            // },
            {
              id: 7,
              name: 'Institution-Wise & Level-Wise Intake And Student Enrolment In 1st Year- ALL',
              reportNumber: 'Report 127',
              showState:false
            },
            {
              id: 8,
              name: 'Institution-Wise & Level-Wise Intake And Student Enrolment In 1st Year - University',
              reportNumber: 'Report 127A',
              showState:false
            },
            {
              id: 9,
              name: 'Institution-Wise & Level-Wise Intake And Student Enrolment In 1st Year- College',
              reportNumber: 'Report 127B',
              showState:false
            },
            {
              id: 10,
              name: 'Institution-Wise & Level-Wise Intake And Student Enrolment In 1st Year - Standalone Institutions',
              reportNumber: 'Report 127C',
              showState:false
            },
          ],
        },
      ],
    },
    {
      title: 'Programmes & Discipline',
      reports1: [
        {
          subTitle: 'Discipline',
          subreports: [
            {
              id: 1,
              name: 'Discipline Group & Level & Gender-Wise Student Enrolment',
              reportNumber: 'Report 38A',
              showState:false
            },
            {
              id: 2,
              name: 'Discipline & Level & Gender-Wise Student Enrolment - ALL',
              reportNumber: 'Report 109',
              showState:false
            },
            {
              id: 3,
              name: 'Discipline & Level & Gender-Wise Student Enrolment - University',
              reportNumber: 'Report 109A',
              showState:false
            },
            {
              id: 4,
              name: 'Discipline & Level & Gender-Wise Student Enrolment - College',
              reportNumber: 'Report 109B',
              showState:false
            },
            {
              id: 5,
              name: 'Discipline & Level & Gender-Wise Student Enrolment - Standalone Institutions',
              reportNumber: 'Report 109C',
              showState:false
            },
            {
              id:6,
              name:'Discipline & Level-Wise Foreign Students Enrolment - ALL',
              reportNumber: 'Report 121',
              showState:false
            },
            {
              id:7,
              name:'Discipline & Level-Wise Foreign Students Enrolment - University',
              reportNumber: 'Report 121A',
              showState:false
            },
            {
              id:8,
              name:'Discipline & Level-Wise Foreign Students Enrolment - College',
              reportNumber: 'Report 121B',
              showState:false
            },
            {
              id:9,
              name:'Discipline & Level-Wise Foreign Students Enrolment - Standalone Institutions',
              reportNumber: 'Report 121C',
              showState:false
            }

          ],
        },
        {
          subTitle: 'Programme',
          subreports: [
            {
              id:1,
              name: 'Programme & Gender-Wise Student Enrolment',
              reportNumber: 'Report 38B',
              showState:false
            },
            {
              id:2,
              name: 'Programme & Institution Wise Male & Female Student Enrolment',
              reportNumber: 'Report 38C',
              showState:false
            },
            {
              id:3,
              name: 'Programme & Institution Wise Male & Female Student Enrolment',
              reportNumber: 'Report 65',
              showState:false
            },

            {
              id:4,
              name: 'Programme & Category & Gender-Wise Student Enrolment - ALL',
              reportNumber: 'Report 110',
              showState:false
            },
            {
              id:5,
              name: 'Programme & Category & Gender-Wise Student Enrolment - University',
              reportNumber: 'Report 110A',
              showState:false
            },
            {
              id:6,
              name: 'Programme & Category & Gender-Wise Student Enrolment - College',
              reportNumber: 'Report 110B',
              showState:false
            },
            {
              id:7,
              name: 'Programme & Category & Gender-Wise Student Enrolment - Standalone Institutions',
              reportNumber: 'Report 110C',
              showState:false
            },
            {
              id:8,
              name: '	Programme-Wise Foreign Students Enrolment',
              reportNumber: 'Report 120',
              showState:false
            },
            {
              id:9,
              name:'Programme & Year-Wise Student Enrolment- ALL',
              reportNumber: 'Report 126',
              showState:false
            },
            {
              id:10,
              name: 'Programme & Year-Wise Student Enrolment - University',
              reportNumber: 'Report 126A',
              showState:false
            },
            {
              id:11,
              name:'Programme & Year-Wise Student Enrolment - College',
              reportNumber: 'Report 126B',
              showState:false
            },
            {
              id:12,
              name:'Programme & Year-Wise Student Enrolment - Standalone Institutions',
              reportNumber: 'Report 126C',
              showState:false
            },
            {
              id:13,
              name:'Programme & Category-Wise Male & Female Student Enrolment At Integrated Level - ALL',
              reportNumber: 'Report 150',
              showState:false
            },
            {
              id:14,
              name:'Programme & Category-Wise Male & Female Student Enrolment At Integrated Level - University',
              reportNumber: 'Report 150A',
              showState:false
            },
            {
              id:15,
              name:'Programme & Category-Wise Male & Female Student Enrolment At Integrated Level - College',
              reportNumber: 'Report 150B',
              showState:false
            },
            {
              id:16,
              name:'Programme & Category-Wise Male & Female Student Enrolment At Integrated Level - Standalone Institutions',
              reportNumber: 'Report 150C',
              showState:false
            },
          ],
        },
      ],
    },
    {
      title: 'Out Turn',
      reports: [

            {
              id: 1,
              name: 'State & Level-Wise Out-Turn',
              reportNumber: 'Report 52',
              showState:false
            },
            {
              id: 2,
              name: 'Level-Wise Out-Turn In University & Its Colleges',
              reportNumber: 'Report 53',
              showState:false
            },
            {
              id: 3,
              name: 'Level-Wise Out-Turn In Various Types Of Universities & Its Colleges',
              reportNumber: 'Report 54',
              showState:false
            },
            {
              id: 4,
              name: 'Level-Wise Out-Turn In Institutions',
              reportNumber: 'Report 55',
              showState:false
            },
            {
              id: 5,
              name: 'Discipline Group & Level-Wise Out-Turn',
              reportNumber: 'Report 55A',
              showState:false
            },
            {
              id: 6,
              name: 'Programmes-Wise Out-Turn In Institutions',
              reportNumber: 'Report 55B',
              showState:false
            },
            {
              id: 7,
              name: 'Institution-Wise Out-Turn',
              reportNumber: 'Report 145',
              showState:false
            },

      ],
    },
    {
      title: 'Infrastructure',
      reports: [

            {
              id: 1,
              name: 'University-Wise Availability Of Infrastructure',
              reportNumber: 'Report 56',
              showState:false
            },
            {
              id: 2,
              name: 'College-Wise Availability Of Infrastructure',
              reportNumber: 'Report 57',
              showState:false
            },
            {
              id: 3,
              name: 'Standalone Institution-Wise Availability Of Infrastructure',
              reportNumber: 'Report 58',
              showState:false
            },
            {
              id: 4,
              name: 'Institution-Wise Number of Students Residing in Hostels - ALL',
              reportNumber: 'Report 111',
              showState:false
            },
            {
              id: 5,
              name: 'Institution-Wise Number of Students Residing in Hostels - University',
              reportNumber: 'Report 111A',
              showState:false
            },
            {
              id: 6,
              name: 'Institution-Wise Number of Students Residing in Hostels - College',
              reportNumber: 'Report 111B',
              showState:false
            },
            {
              id: 7,
              name: 'Institution-Wise Number of Students Residing in Hostels - Standalone Institutions',
              reportNumber: 'Report 111C',
              showState:false
            },
            {
              id: 8,
              name: 'Number of Students Residing in Hostels In University & Its Colleges - ALL',
              reportNumber: 'Report 112',
              showState:false
            },
            {
              id: 9,
              name: 'Institution-Wise Availability Of Infrastructure',
              reportNumber: 'Report 131',
              showState:false
            },
            {
              id: 10,
              name: 'Institution-Wise Number Of Staff Quarters',
              reportNumber: 'Report 139',
              showState:false
            },
            {
              id: 11,
              name: 'Number Of Staff Quarters In University & Its Colleges',
              reportNumber: 'Report 140',
              showState:false
            },


      ],
    },
    {
      title: 'Pupil Teacher Ratio',
      reports: [

            {
              id: 1,
              name: 'State-Wise Pupil Teacher Ratio In Different Types Of Institutions',
              reportNumber: 'Report 64',
              showState:false
            },
            {
              id: 2,
              name: 'Institution-Wise Pupil-Teacher Ratio',
              reportNumber: 'Report 143',
              showState:false
            },
            {
              id: 3,
              name: 'State-Wise Pupil-Teacher Ratio',
              reportNumber: 'Report 144',
              showState:false
            },

      ],
    },


   

    {
      title: 'List Of Reporting Institutions',
      reports: [
            {
              id: 1,
              name: 'State-wise List of Universities & Attached Institutions',
              reportNumber: 'Report 4',
              showState:false
            },
            {
              id: 2,
              name: 'State-wise List of Stand-Alone Institutions',
              reportNumber: 'Report 5',
              showState:false
            },
            {
              id: 3,
              name: '	Details Of Head Of The Institutions',
              reportNumber: 'Report 142',
              showState:false
            },

      ],
    },

    {
      title: 'Gender Ratio',
      reports: [
            {
              id: 1,
              name: 'State & Category-Wise Gender Ratio For Student',
              reportNumber: 'Report 44',
              showState:false
            },
            {
              id: 2,
              name: 'State & Level-Wise Gender Ratio For Student',
              reportNumber: 'Report 45',
              showState:false
            },
            {
              id: 3,
              name: 'State & Level-Wise Gender Ratio In Self-Financing Mode',
              reportNumber: 'Report 46',
              showState:false
            },
            {
              id: 4,
              name: 'State & Management-Wise Gender Ratio For Student In Universities',
              reportNumber: 'Report 47A',
              showState:false
            },
            {
              id: 5,
              name: 'State & Management-Wise Gender Ratio For Student In Colleges',
              reportNumber: 'Report 47B',
              showState:false
            }, {
              id: 6,
              name: 'State & Management-Wise Gender Ratio For Student In Standalone Institutions',
              reportNumber: 'Report 47C',
              showState:false
            },
            {
              id: 7,
              name: 'Level-Wise Gender Ratio For Student In University',
              reportNumber: 'Report 48',
              showState:false
            },
            {
              id: 8,
              name: 'Level-Wise Gender Ratio For Student In Various Types Of Universities & Its Colleges',
              reportNumber: 'Report 49',
              showState:false
            },
            {
              id: 9,
              name: 'Institution & Category-Wise Gender Ratio For Student',
              reportNumber: 'Report 50',
              showState:false
            },
            {
              id: 10,
              name: 'Institution & Level-Wise Gender Ratio For Student',
              reportNumber: 'Report 51',
              showState:false
            },
            {
              id: 11,
              name: 'Discipline Group & Level-Wise Gender Ratio For Student - All India',
              reportNumber: 'Report 51A',
              showState:false
            },
            {
              id: 12,
              name: 'Programme-Wise Gender Ratio For Student - All India',
              reportNumber: 'Report 51B',
              showState:false
            },
      ],
    },

    {
      title: 'Time Series',
      reports1: [
        {
          subTitle: 'Student Enrolment',
          subreports: [
            {
              id: 1,
              name: 'Year & Level-Wise Male & Female Student Enrolment - ALL',
              reportNumber: 'Report 100',
              showState:false
            },
            {
              id: 2,
              name: 'Year & Level-Wise Male & Female Student Enrolment - University',
              reportNumber: 'Report 100A',
              showState:false
            },
            {
              id: 3,
              name: 'Year & Level-Wise Male & Female Student Enrolment - College',
              reportNumber: 'Report 100B',
              showState:false
            },
            {
              id: 4,
              name: 'Year & Level-Wise Male & Female Student Enrolment - Standalone Institutions',
              reportNumber: 'Report 100C',
              showState:false
            },
            {
              id: 5,
              name: 'Year & Category-Wise Male & Female Student Enrolment - ALL',
              reportNumber: 'Report 101',
              showState:false
            },
            {
              id: 6,
              name: 'Year & Category-Wise Male & Female Student Enrolment - University',
              reportNumber: 'Report 101A',
              showState:false
            },
            {
              id: 7,
              name: 'Year & Category-Wise Male & Female Student Enrolment - College',
              reportNumber: 'Report 101B',
              showState:false
            },
            {
              id: 8,
              name: 'Year & Category-Wise Male & Female Student Enrolment - Standalone Institutions',
              reportNumber: 'Report 101C',
              showState:false
            },
            {
              id: 9,
              name: 'Year-Wise Number of Institutions, Student Enrolment and Teachers - ALL',
              reportNumber: 'Report 102',
              showState:false
            },
            {
              id: 10,
              name: 'Year-Wise Number of Institutions, Student Enrolment and Teachers - University',
              reportNumber: 'Report 102A',
              showState:false
            },
            {
              id: 11,
              name: 'Year-Wise Number of Institutions, Student Enrolment and Teachers - College',
              reportNumber: 'Report 102B',
              showState:false
            },
            {
              id: 12,
              name: 'Year-Wise Number of Institutions, Student Enrolment and Teachers - Standalone Institutions',
              reportNumber: 'Report 102C',
              showState:false
            },
            {
              id: 13,
              name: 'Institution & Level-Wise Male and Female Student Enrolment During Last Three Years - ALL',
              reportNumber: 'Report 103',
              showState:false
            },
            {
              id: 14,
              name: 'Institution & Level-Wise Male and Female Student Enrolment During Last Three Years - University',
              reportNumber: 'Report 103A',
              showState:false
            },
            {
              id: 15,
              name: 'Institution & Level-Wise Male and Female Student Enrolment During Last Three Years - College',
              reportNumber: 'Report 103B',
              showState:false
            },
            {
              id: 16,
              name: 'Institution & Level-Wise Male and Female Student Enrolment During Last Three Years - Standalone Institutions',
              reportNumber: 'Report 103C',
              showState:false
            },
            {
              id: 17,
              name: 'Institution & Category-Wise Male and Female Student Enrolment During Last Three Years - ALL',
              reportNumber: 'Report 104',
              showState:false
            },
            {
              id: 18,
              name: 'Institution & Category-Wise Male and Female Student Enrolment During Last Three Years - University',
              reportNumber: 'Report 104A',
              showState:false
            },
            {
              id: 19,
              name: 'Institution & Category-Wise Male and Female Student Enrolment During Last Three Years - College',
              reportNumber: 'Report 104B',
              showState:false
            },
            {
              id: 20,
              name: 'Institution & Category-Wise Male and Female Student Enrolment During Last Three Years - Standalone Institutions',
              reportNumber: 'Report 104C',
              showState:false
            },
            {
              id: 21,
              name: 'State & Level-Wise Male and Female Student Enrolment During Last Three Years - ALL',
              reportNumber: 'Report 105',
              showState:false
            },
            {
              id: 22,
              name: 'State & Level-Wise Male and Female Student Enrolment During Last Three Years - University',
              reportNumber: 'Report 105A',
              showState:false
            },
            {
              id: 23,
              name: 'State & Level-Wise Male and Female Student Enrolment During Last Three Years - College',
              reportNumber: 'Report 105B',
              showState:false
            },
            {
              id: 24,
              name: 'State & Level-Wise Male and Female Student Enrolment During Last Three Years - Standalone Institutions',
              reportNumber: 'Report 105C',
              showState:false
            },
            {
              id: 25,
              name: 'State & Category-Wise Male and Female Student Enrolment During Last Three Years - ALL',
              reportNumber: 'Report 106',
              showState:false
            },
            {
              id: 26,
              name: 'State & Category-Wise Male and Female Student Enrolment During Last Three Years - University',
              reportNumber: 'Report 106A',
              showState:false
            },
            {
              id: 27,
              name: 'State & Category-Wise Male and Female Student Enrolment During Last Three Years - College',
              reportNumber: 'Report 106B',
              showState:false
            },
            {
              id: 28,
              name: 'State & Category-Wise Male and Female Student Enrolment During Last Three Years - Standalone Institutions',
              reportNumber: 'Report 106C',
              showState:false
            },

        ],
        },
        {
          subTitle: 'Teaching Staff',
          subreports: [
            {
              id: 1,
              name: 'State & Year-Wise Male & Female Teacher - ALL',
              reportNumber: 'Report 124',
              showState:false
            },
            {
              id: 2,
              name: 'State & Year-Wise Male & Female Teacher - University',
              reportNumber: 'Report 124A',
              showState:false
            },
            {
              id: 3,
              name: 'State & Year-Wise Male & Female Teacher - College',
              reportNumber: 'Report 124B',
              showState:false
            },
            {
              id: 4,
              name: 'State & Year-Wise Male & Female Teacher - Standalone Institutions',
              reportNumber: 'Report 124C',
              showState:false
            },
            {
              id: 5,
              name: 'Year & Category-Wise Male & Female Teacher - ALL',
              reportNumber: 'Report 125',
              showState:false
            },
            {
              id: 6,
              name: 'Year & Category-Wise Male & Female Teacher - University',
              reportNumber: 'Report 125A',
              showState:false
            },
            {
              id: 7,
              name: 'Year & Category-Wise Male & Female Teacher - College',
              reportNumber: 'Report 125B',
              showState:false
            },
            {
              id: 8,
              name: 'Year & Category-Wise Male & Female Teacher - Standalone Institutions',
              reportNumber: 'Report 125C',
              showState:false
            },
            {
              id: 9,
              name: 'Post-Wise Sanctioned Strength And Teaching Staff In Position During Last Three Years - ALL',
              reportNumber: 'Report 171',
              showState:false
            },
          ],
        },
        {
          subTitle: 'Non-Teaching Staff',
          subreports: [
            {
              id: 1,
              name: 'State & Year-Wise Male & Female Staff During Last Three Years - ALL',
              reportNumber: 'Report 122',
              showState:false
            },
            {
              id: 2,
              name: 'State & Year-Wise Male & Female Staff During Last Three Years - University',
              reportNumber: 'Report 122A',
              showState:false
            },
            {
              id: 3,
              name: 'State & Year-Wise Male & Female Staff During Last Three Years - College',
              reportNumber: 'Report 122B',
              showState:false
            },
            {
              id: 4,
              name: 'State & Year-Wise Male & Female Staff During Last Three Years - Standalone Institutions',
              reportNumber: 'Report 122C',
              showState:false
            },
            {
              id: 5,
              name: 'Year & Category-Wise Male & Female Staff - ALL',
              reportNumber: 'Report 123',
              showState:false
            },
            {
              id: 6,
              name: 'Year & Category-Wise Male & Female Staff - University',
              reportNumber: 'Report 123A',
              showState:false
            },
            {
              id: 7,
              name: 'Year & Category-Wise Male & Female Staff - College',
              reportNumber: 'Report 123B',
              showState:false
            },
            {
              id: 8,
              name: 'Year & Category-Wise Male & Female Staff - Standalone Institutions',
              reportNumber: 'Report 123C',
              showState:false
            },
            {
              id: 9,
              name: ' Post-Wise Sanctioned Strength And Non-Teaching Staff In Position During Last Three Years - ALL',
              reportNumber: 'Report 172',
              showState:false
            },
          ],
        },
      ],
    },
    {
      title:'Others',
     reports: [
        {
          id: 1,
          name: 'Valid Accreditation Of The Institution',
          reportNumber: 'Report 128',
          showState:false
        },
        {
          id: 2,
          name: 'List Of Colleges Running Only Diploma Level Course',
          reportNumber: 'Report 130',
          showState:false
        },
        {
          id: 3,
          name: 'Remarks Recorded By The Institutions',
          reportNumber: 'Report 132',
          showState:false
        },
        {
          id: 4,
          name: 'Institution-Wise Number Of Students Getting Scholarship',
          reportNumber: 'Report 113',
          showState:false
        },
        {
          id: 5,
          name: 'State-Wise Number Of Students Getting Scholarship',
          reportNumber: 'Report 114',
          showState:false
        },
        {
          id: 6,
          name: 'Institution-Wise Number Of Students Availing Education Loan',
          reportNumber: 'Report 118',
          showState:false
        },
        {
          id: 7,
          name: 'State-Wise Number Of Students Availing Education Loan',
          reportNumber: 'Report 119',
          showState:false
        },
      ]
    },

    {
      title: 'UGC Reports',
      reports: [
            {
              id: 1,
              name: 'State & UGC Faculty Group Wise Student Enrollment In Universities & Colleges',
              reportNumber: 'Report 133',
              showState:false
            },
            {
              id: 2,
              name: 'UGC Faculty Group & Level-Wise Male & Female Student Enrolment',
              reportNumber: 'Report 134',
              showState:false
            },
            {
              id: 3,
              name: 'UGC Programme & Discipline-Wise Male & Female Student Out-Turn',
              reportNumber: 'Report 134A',
              showState:false
            },
            {
              id: 4,
              name: 'State-wise/UGC Faculty Group- wise/University- wise/ Level-Wise Male & Female Pooled Students Enrolment',
              reportNumber: 'Report 151',
              showState:false
            },
            // {
            //   id: 5,
            //   name: 'State-wise/University-wise/Designation-wise Male & Female Pooled Teaching staff',
            //   reportNumber: 'Report 152',
            // },
      ],
    },


    {
      title: 'Data Sharing',
      reports: [
            {
              id: 1,
              name: 'Unit Level Data',
              reportNumber: 'downloadUnitLevelData',
              showState:true
            },

            {
              id: 2,
              name: 'Pooled Data',
              reportNumber: 'pooledData',
              showState:false
            },
            {
              id: 3,
              name: 'University Comparison Data',
              reportNumber: 'universityComparisonData',
              showState:false
            },
            {
              id: 4,
              name: 'College Comparison Data',
              reportNumber: 'collegeComparisonData',
              showState:false
            },
            {
              id: 5,
              name: 'Standalone Comparison Data',
              reportNumber: 'standaloneComparisonData',
              showState:false
            },
            {
              id: 6,
              name: 'Download user uploaded PDF Forms',
              reportNumber: 'downloadDcfData',
              showState:false
            },
            {
              id: 7,
              name: 'DCF Data For University',
              reportNumber: 'downloadDcfData?institutionType=U',
              showState:false
            },
            {
              id: 8,
              name: 'DCF Data For College',
              reportNumber: "downloadDcfData?institutionType=C",
              showState:false
            },
            {
              id: 9,
              name: 'DCF Data For Standalone',
              reportNumber: "downloadDcfData?institutionType=S",
              showState:false
            },

      ],
  //     reports2: [
  //       {
  //         id: 1,
  //         name: 'Unit Level Data',
  //         reportNumber: 'downloadUnitLevelData',
  //         showState:true

  //       },

  // ],
    },

    {
      title: 'Know Your Institutions(For KYC Portal)',
      reports: [
            {
              id: 1,
              name: 'Know Your Institutions',
              reportNumber: 'downloadKnowYourInstitute=I',
              showState:false

            },
            {
              id: 2,
              name: 'Know Your University',
              reportNumber: 'downloadKnowYourUniversity=U',
              showState:false
            },
            {
              id: 3,
              name: 'Know Your College',
              reportNumber: 'downloadKonwYourCollege=C',
              showState:false

            },
            {
              id: 4,
              name: 'Know Your Standalone Institute',
              reportNumber: "downloadKnowYourStandaloneInstitute=S",
              showState:false
            },
      ],
    },

    {
      title: 'Finance',
      reports: [
            {
              id: 1,
              name: 'Institution-Wise Receipts & Expenditure',
              reportNumber: 'Report 62',
              showState:false
            },
            {
              id: 2,
              name: 'Institution-Wise Expenditure Per Student',
              reportNumber: 'Report 63',
              showState:false
            },
      ],
    },
    {
      title: 'Progress Monitoring',
      reports: [
            {
              id: 1,
              name: 'Progress Monitoring for Universities',
              reportNumber: 'Report 59',
              showState:false
            },
            {
              id: 2,
              name: 'Progress Monitoring for Colleges',
              reportNumber: 'Report 60',
              showState:false
            },
            {
              id: 3,
              name: 'Progress Monitoring for Standalone Institutions',
              reportNumber: 'Report 61',
              showState:false
            },
            // {
            //   id: 4,
            //   name: 'Progress Monitoring for Other Minority Form',
            //   reportNumber: 'Report 96',
            // },
            // {
            //   id: 4,
            //   name: 'DCF-VI: List of Eligible Universities',
            //   reportNumber: 'Report 161',
            // },
            // {
            //   id: 4,
            //   name: 'DCF-VI: List of Eligible Colleges',
            //   reportNumber: 'Report 162',
            // },
            // {
            //   id: 5,
            //   name: 'DCF-VI: List of Eligible Standalone Institutions',
            //   reportNumber: 'Report 163',
            // },
      ],
    },
    // {
    //   title: 'Remuneration',
    //   reports: [
    //         {
    //           id: 1,
    //           name: 'Institution-Wise Details of Remuneration',
    //           reportNumber: 'Report 91',
    //         },
    //         {
    //           id: 2,
    //           name: 'Institution-Wise Details of Remuneration (Updated account only)',
    //           reportNumber: 'Report 91A',
    //         },
    //   ],
    // },
    {
      title: 'Data User',
      reports: [
            {
              id: 1,
              name: 'Progress Monitoring for Data User',
              reportNumber: 'Report 97',
              showState:false
            },
      ],
    },
    // {
    //   title: 'Institution Contact Details',
    //   reports: [
    //         {
    //           id: 1,
    //           name: 'Institution Contact Report',
    //           reportNumber: 'Report 156',
    //           showState:false
    //         },
    //         {
    //           id: 2,
    //           name: 'SMC User Details',
    //           reportNumber: 'Report 167',
    //           showState:false
    //         },
    //   ],
    // },
    {
      title: 'List Of Institutions',
      reports: [

            {
              id: 1,
              name: 'List Of All Colleges',
              reportNumber: 'Report 66',
              showState:false
            },
            {
              id: 2,
              name: 'List Of All Universities',
              reportNumber: 'Report 141',
              showState:false
            },
            {
              id: 3,
              name: 'List of Standalone Institutions',
              reportNumber: 'Report 146',
              showState:false
            },

      ],
    },
    {
      title: 'Institution Management',
      reports: [

            {
              id: 1,
              name: 'List Of Deaffiliated Colleges',
              reportNumber: 'Report 154',
              showState:false
            },
            // {
            //   id: 2,
            //   name: 'Exception',
            //   reportNumber: 'Report 155',
            // },
            {
              id: 2,
              name: 'List of Inactive Standalone Institutions',
              reportNumber: 'Report 164',
              showState:false
            },

      ],
    },

    {
      title: 'Request Details For Adding Institution',
      reports: [
            {
              id: 1,
              name: 'College Institutions',
              reportNumber: 'Report 148',
              showState:false
            },
            {
              id: 2,
              name: 'Standalone Institutions',
              reportNumber: 'Report 149',
              showState:false
            },
      ],
    },
  ],
  userData: [
    {
      title: 'Basic Reports',
      reports: [
        {
          id: 1,
          name: 'University Report',
          reportNumber: 'Report 1',
          showState:false
        },
        {
          id: 2,
          name: 'College Report',
          reportNumber: 'Report 2',
          showState:false
        },
        {
          id: 3,
          name: 'Standalone Institution Report',
          reportNumber: 'Report 3',
          showState:false
        },
      ],
    },
    {
      title: 'Number Of Institutions',
      reports: [
        {
          id: 1,
          name: 'State-Wise Number of Institutions',
          reportNumber: 'Report 6',
          showState:false
        },
        {
          id: 2,
          name: 'State-Wise Number of Institutions - Urban',
          reportNumber: 'Report 7',
          showState:false
        },
        {
          id: 3,
          name: 'State-Wise Number of Institutions - Rural',
          reportNumber: 'Report 8',
          showState:false
        },
        {
          id: 4,
          name: 'State & Specialisation - Wise Number Of Institutions',
          reportNumber: 'Report 9',
          showState:false
        },
        // {
        //   id: 5,
        //   name: 'State-Wise Number Of Colleges By Recognition',
        //   reportNumber: 'Report 10',
        //   showState:false
        // },
        // {
        //   id: 6,
        //   name: 'Type-Wise Number Of Institutions Attached With University',
        //   reportNumber: 'Report 12',
        //   showState:false
        // },
        {
          id: 7,
          name:
            'Management-Wise Number Of Institutions Attached With University',
          reportNumber: 'Report 13',
          showState:false
        },
        {
          id: 8,
          name:
            'State-Wise Number Of University Offering Education Through Distance Mode',
          reportNumber: 'Report 14',
          showState:false
        },
      ],
    },

    {
      title: 'Teaching Staff',
      reports1: [
        {
          subTitle: 'State-wise',
          subreports: [
            {
              id: 1,
              name: 'State & Post & Gender-Wise Number of Teacher',
              reportNumber: 'Report 15',
              showState:false
            },
            {
              id: 2,
              name: 'State & Category & Gender-Wise Number of Teacher',
              reportNumber: 'Report 16',
              showState:false
            },
            // {
            //   id: 3,
            //   name: 'State-Wise Sanctioned Strength And Teaching Staff In Position',
            //   reportNumber: 'Report 138',
            // },
          ],
        },
        {
          subTitle: 'District-wise',
          subreports: [
            {
              id: 1,
              name: 'District & Category & Gender-Wise Teacher - ALL',
              reportNumber: 'Report 116',
              showState:false
            },
            {
              id: 2,
              name: 'District & Category & Gender-Wise Teacher - University',
              reportNumber: 'Report 116A',
              showState:false
            },
            {
              id: 3,
              name: 'District & Category & Gender-Wise Teacher - College',
              reportNumber: 'Report 116B',
              showState:false
            },
            {
              id: 4,
              name:
                'District & Category & Gender-Wise Teacher - Standalone Institutions',
              reportNumber: 'Report 116C',
              showState:false
            },
          ],
        },
        {
          subTitle: 'Institution-wise',
          subreports: [
            // {
            //   id: 1,
            //   name: 'Post-Wise Number Of Male & Female Teacher In University & Its Colleges',
            //   reportNumber: 'Report 17',
            // },
            // {
            //   id: 2,
            //   name: 'Category-Wise Number Of Male & Female Teacher In University & Its Colleges',
            //   reportNumber: 'Report 18',
            // },
            {
              id: 1,
              name: 'Post & Gender-Wise Number of Teacher In Institution',
              reportNumber: 'Report 19',
              showState:false
            },
            {
              id: 2,
              name: 'Category & Gender-Wise Number of Teacher In Institution',
              reportNumber: 'Report 20',
              showState:false
            },
            // {
            //    id:5,
            //    name: 'Post-Wise number Of Male & Female Teacher In Various Types Of Universities & Its Colleges',
            //    reportNumber:'Report 21',
            // },
            // {
            //   id: 6,
            //   name: 'Category-Wise Number Of Male & Female Teacher In Various Types Of Universities & Its Colleges',
            //   reportNumber: 'Report 22',
            // },
            // {
            //   id: 7,
            //   name: 'Faculty & Department-Wise Number Of Male & Female Teacher In Each Institution',
            //   reportNumber: 'Report 22A',
            // },
            // {
            //   id: 8,
            //   name: 'Post-Wise Sanctioned Strength And Teaching Staff In Position',
            //   reportNumber: 'Report 137',
            // },
            // {
            //   id: 9,
            //   name: 'Country and Post-Wise Number of Teachers',
            //   reportNumber: 'Report 165',
            // },
            // {
            //   id: 10,
            //   name: 'Country and Post-Wise Number of Teachers',
            //   reportNumber: 'Report 166',
            // },
          ],
        },
      ],
    },
    {
      title: 'Non Teaching Staff',
      reports1: [
        {
          subTitle: 'State-wise',
          subreports: [
            {
              id: 1,
              name: 'State & Post & Gender-Wise Number of Non-Teaching Staff',
              reportNumber: 'Report 23',
              showState:false
            },
            {
              id: 2,
              name: 'State & Category & Gender-Wise Number of Non-Teaching Staff',
              reportNumber: 'Report 24',
              showState:false
            },
            // {
            //   id: 3,
            //   name: 'State-Wise Sanctioned Strength And NON-Teaching Staff In Position',
            //   reportNumber: 'Report 136',
            // },
          ],
        },
        {
          subTitle: 'District-wise',
          subreports: [
            {
              id: 1,
              name: 'District & Category & Gender-Wise Staff - ALL',
              reportNumber: 'Report 117',
              showState:false
            },
            {
              id: 2,
              name: 'District & Category & Gender-Wise Staff - University',
              reportNumber: 'Report 117A',
              showState:false
            },
            {
              id: 3,
              name: 'District & Category & Gender-Wise Staff - College',
              reportNumber: 'Report 117B',
              showState:false
            },
            {
              id: 4,
              name:
                'District & Category & Gender-Wise Standalone Institutions',
              reportNumber: 'Report 117C',
              showState:false
            },
          ],
        },
        {
          subTitle: 'Institution-wise',
          subreports: [
            // {
            //   id: 1,
            //   name: 'Post-Wise Number Of Male & Female Non-Teaching Staff In University & Its Colleges',
            //   reportNumber: 'Report 25',
            // },
            // {
            //   id: 2,
            //   name: 'Category-Wise Number Of Male & Female Non-Teaching Staff In University & Its Colleges',
            //   reportNumber: 'Report 26',
            // },
            {
              id: 1,
              name: 'Institution & Post & Gender-Wise Number of Non-Teaching Staff',
              reportNumber: 'Report 27',
              showState:false
            },
            {
              id: 2,
              name: 'Institution & Category & Gender-Wise Number of Non-Teaching Staff',
              reportNumber: 'Report 28',
              showState:false
            },
            // {
            //   id: 5,
            //   name: 'Post-Wise number Of Male & Female Non-Teaching Staff In Various Types Of Universities & Its Colleges',
            //   reportNumber: 'Report 29',
            // },
            // {
            //   id: 6,
            //   name: 'Category-Wise Number Of Male & Female Non-Teaching Staff In Various Types Of Universities & Its Colleges',
            //   reportNumber: 'Report 30',
            // },
            // {
            //   id: 7,
            //   name: 'Post-Wise Sanctioned Strength And NON-Teaching Staff In Position',
            //   reportNumber: 'Report 135',
            // },
          ],
        },
      ],
    },
    {
      title: 'Student Enrolment',
      reports1: [
        {
          subTitle: 'State-wise',
          subreports: [
            {
              id: 1,
              name: 'State & Level & Gender-Wise Student Enrolment',
              reportNumber: 'Report 31',
              showState:false
            },
            {
              id: 2,
              name: 'State & Level-Wise & Gender-Wise Student Enrolment Through Distance Mode Of Education',
              reportNumber: 'Report 32',
              showState:false
            },
            // {
            //   id: 3,
            //   name: 'State-Wise & Gender-Wise Student Enrolment In Regional Centers Of University Offering Education Through Distance Mode',
            //   reportNumber: 'Report 32A',
            // },
            // {
            //   id: 4,
            //   name: 'State & Category & Gender-Wise Student Enrolment In Regional Centers Of University Offering Education Through Distance Mode',
            //   reportNumber: 'Report 32B',
            // },
            // {
            //   id: 5,
            //   name: 'State & Level-Wise & Gender-Wise Student On Roll In Distance Mode Of Education',
            //   reportNumber: 'Report 33',
            // },
            // {
            //   id: 6,
            //   name: 'State & Level-Wise & Gender-Wise Student Enrolment In Self-Financing Mode',
            //   reportNumber: 'Report 34',
            // },
             {
              id: 3,
              name: 'State & Management & Gender-Wise Student Enrolment In Universities',
              reportNumber: 'Report 35A',
              showState:false
            },
            {
              id: 4,
              name: 'State & Management & Gender-Wise Student Enrolment In Colleges',
              reportNumber: 'Report 35B',
              showState:false
            },
            {
              id: 5,
              name: 'State & Management & Gender-Wise Student Enrolment In Standalone Institutions',
              reportNumber: 'Report 35C',
              showState:false
            },
            {
              id: 6,
              name: 'State & Category & Gender-Wise Student Enrolment',
              reportNumber: 'Report 39',
              showState:false
            },
            // {
            //   id: 11,
            //   name: 'State & Level-Wise Intake And Student Enrolment In 1st Year',
            //   reportNumber: 'Report 43',
            // },
            // {
            //   id: 11,
            //   name: 'Country & Level & Gender-Wise Foreign Student Enrolment',
            //   reportNumber: 'Report 169',
            // },
            // {
            //   id: 12,
            //   name: 'State & Category & Gender-Wise Student Enrolment',
            //   reportNumber: 'Report 170',
            // },
          ],
        },
        {
          subTitle: 'District-wise',
          subreports: [
            {
              id: 1,
              name: 'District & Level-Wise & Gender-Wise Student Enrolment - ALL',
              reportNumber: 'Report 107',
              showState:false
            },
            {
              id: 2,
              name: 'District & Level-Wise & Gender-Wise Student Enrolment - University',
              reportNumber: 'Report 107A',
              showState:false
            },
            {
              id: 3,
              name: 'District & Level-Wise & Gender-Wise Student Enrolment - College',
              reportNumber: 'Report 107B',
              showState:false
            },
            {
              id: 4,
              name:
                'District & Level-Wise & Gender-Wise Student Enrolment - Standalone Institutions',
              reportNumber: 'Report 107C',
              showState:false
            },
            {
              id: 5,
              name:
                'District & Category-Wise & Gender-Wise Student Enrolment - ALL',
              reportNumber: 'Report 108',
              showState:false
            },
            {
              id: 6,
              name:
                'District & Category-Wise & Gender-Wise Student Enrolment - University',
              reportNumber: 'Report 108A',
              showState:false
            },
            {
              id: 7,
              name:
                'District & Category-Wise & Gender-Wisee Student Enrolment - College',
              reportNumber: 'Report 108B',
              showState:false
            },
            
            {
              id: 8,
              name:
                'District & Category-Wise & Gender-Wise Student Enrolment - Standalone Institutions',
              reportNumber: 'Report 108C',
              showState:false
            },

          ],
        },
        {
          subTitle: 'Institution-wise',
          subreports: [
            // {
            //   id: 1,
            //   name: 'Level-Wise & Gender-Wise Student Enrolment In University & Its Colleges',
            //   reportNumber: 'Report 36',
            // },
            // {
            //   id: 2,
            //   name: 'Level-Wise & Gender-Wise Student Enrolment In Various Types Of University & Its Colleges',
            //   reportNumber: 'Report 37',
            // },
            {
              id: 1,
              name: 'Institution & LeveL & Gender-Wise Student Enrolment',
              reportNumber: 'Report 38',
              showState:false
            },
            // {
            //   id: 4,
            //   name: 'Category-Wise & Gender-Wise Student Enrolment In University & Its Colleges',
            //   reportNumber: 'Report 40',
            // },
            // {
            //   id: 5,
            //   name: 'Category-Wise & Gender-Wise Student Enrolment In Various Types Of Universities',
            //   reportNumber: 'Report 41',
            // },
            {
              id: 2,
              name: 'Institution & Category & Gender-Wise Student Enrolment',
              reportNumber: 'Report 42',
              showState:false
            },
            {
              id: 3,
              name: 'Student Enrolment In University Teaching Departments & Its attached Colleges by type',
              reportNumber: 'Report 115',
              showState:false
            },
            // {
            //   id: 7,
            //   name: 'Institution-Wise & Level-Wise Intake And Student Enrolment In 1st Year- ALL',
            //   reportNumber: 'Report 127',
            // },
            // {
            //   id: 8,
            //   name: 'Institution-Wise & Level-Wise Intake And Student Enrolment In 1st Year - University',
            //   reportNumber: 'Report 127A',
            // },
            // {
            //   id: 9,
            //   name: 'Institution-Wise & Level-Wise Intake And Student Enrolment In 1st Year- College',
            //   reportNumber: 'Report 127B',
            // },
            // {
            //   id: 10,
            //   name: 'Institution-Wise & Level-Wise Intake And Student Enrolment In 1st Year - Standalone Institutions',
            //   reportNumber: 'Report 127C',
            // },
          ],
        },
      ],
    },
    {
      title: 'Programmes & Discipline',
      reports1: [
        {
          subTitle: 'Discipline',
          subreports: [
            {
              id: 1,
              name: 'Discipline Group & Level & Gender-Wise Student Enrolment',
              reportNumber: 'Report 38A',
              showState:false
            },
            {
              id: 2,
              name: 'Discipline & Level & Gender-Wise Student Enrolment - ALL',
              reportNumber: 'Report 109',
              showState:false
            },
            {
              id: 3,
              name: 'Discipline & Level & Gender-Wise Student Enrolment - University',
              reportNumber: 'Report 109A',
              showState:false
            },
            {
              id: 4,
              name: 'Discipline & Level & Gender-Wise Student Enrolment - College',
              reportNumber: 'Report 109B',
              showState:false
            },
            {
              id: 5,
              name: 'Discipline & Level & Gender-Wise Student Enrolment - Standalone Institutions',
              reportNumber: 'Report 109C',
              showState:false
            },
            {
              id:6,
              name:'Discipline & Level-Wise Foreign Students Enrolment - ALL',
              reportNumber: 'Report 121',
              showState:false
            },
            {
              id:7,
              name:'Discipline & Level-Wise Foreign Students Enrolment - University',
              reportNumber: 'Report 121A',
              showState:false
            },
            {
              id:8,
              name:'Discipline & Level-Wise Foreign Students Enrolment - College',
              reportNumber: 'Report 121B',
              showState:false
            },
            {
              id:9,
              name:'Discipline & Level-Wise Foreign Students Enrolment - Standalone Institutions',
              reportNumber: 'Report 121C',
              showState:false
            }

          ],
        },
        {
          subTitle: 'Programme',
          subreports: [
            {
              id:1,
              name: 'Programme & Gender-Wise Student Enrolment',
              reportNumber: 'Report 38B',
              showState:false
            },
            // {
            //   id:2,
            //   name: 'Programme & Institution Wise Male & Female Student Enrolment',
            //   reportNumber: 'Report 38C',
            // },
            // {
            //   id:3,
            //   name: 'Programme & Institution Wise Male & Female Student Enrolment',
            //   reportNumber: 'Report 65',
            // },

            {
              id:2,
              name: 'Programme & Category & Gender-Wise Student Enrolment - ALL',
              reportNumber: 'Report 110',
              showState:false
            },
            {
              id:3,
              name: 'Programme & Category & Gender-Wise Student Enrolment - University',
              reportNumber: 'Report 110A',
              showState:false
            },
            {
              id:4,
              name: 'Programme & Category & Gender-Wise Student Enrolment - College',
              reportNumber: 'Report 110B',
              showState:false
            },
            {
              id:5,
              name: 'Programme & Category & Gender-Wise Student Enrolment - Standalone Institutions',
              reportNumber: 'Report 110C',
              showState:false
            },
            {
              id:6,
              name: '	Programme-Wise Foreign Students Enrolment',
              reportNumber: 'Report 120',
              showState:false
            },
            {
              id:7,
              name:'Programme & Year-Wise Student Enrolment- ALL',
              reportNumber: 'Report 126',
              showState:false
            },
            {
              id:8,
              name: 'Programme & Year-Wise Student Enrolment - University',
              reportNumber: 'Report 126A',
              showState:false
            },
            {
              id:9,
              name:'Programme & Year-Wise Student Enrolment - College',
              reportNumber: 'Report 126B',
              showState:false
            },
            {
              id:10,
              name:'Programme & Year-Wise Student Enrolment - Standalone Institutions',
              reportNumber: 'Report 126C',
              showState:false
            },
            // {
            //   id:13,
            //   name:'Programme & Category-Wise Male & Female Student Enrolment At Integrated Level - ALL',
            //   reportNumber: 'Report 150',
            // },
            // {
            //   id:14,
            //   name:'Programme & Category-Wise Male & Female Student Enrolment At Integrated Level - University',
            //   reportNumber: 'Report 150A',
            // },
            // {
            //   id:15,
            //   name:'Programme & Category-Wise Male & Female Student Enrolment At Integrated Level - College',
            //   reportNumber: 'Report 150B',
            // },
            // {
            //   id:16,
            //   name:'Programme & Category-Wise Male & Female Student Enrolment At Integrated Level - Standalone Institutions',
            //   reportNumber: 'Report 150C',
            // },
          ],
        },
      ],
    },
    {
      title: 'Out Turn',
      reports: [

            {
              id: 1,
              name: 'State & Level-Wise Out-Turn',
              reportNumber: 'Report 52',
              showState:false
            },
            // {
            //   id: 2,
            //   name: 'Level-Wise Out-Turn In University & Its Colleges',
            //   reportNumber: 'Report 53',
            // },
            // {
            //   id: 3,
            //   name: 'Level-Wise Out-Turn In Various Types Of Universities & Its Colleges',
            //   reportNumber: 'Report 54',
            // },
            {
              id: 2,
              name: 'Level-Wise Out-Turn In Institutions',
              reportNumber: 'Report 55',
              showState:false
            },
            {
              id: 3,
              name: 'Discipline Group & Level-Wise Out-Turn',
              reportNumber: '	Report 55A',
              showState:false
            },
            {
              id: 4,
              name: 'Programmes-Wise Out-Turn In Institutions',
              reportNumber: 'Report 55B',
              showState:false
            },
            // {
            //   id: 7,
            //   name: 'Institution-Wise Out-Turn',
            //   reportNumber: 'Report 145',
            // },

      ],
    },
    {
      title: 'Infrastructure',
      reports: [

            {
              id: 1,
              name: 'University-Wise Availability Of Infrastructure',
              reportNumber: 'Report 56',
              showState:false
            },
            {
              id: 2,
              name: 'College-Wise Availability Of Infrastructure',
              reportNumber: 'Report 57',
              showState:false
            },
            {
              id: 3,
              name: 'Standalone Institution-Wise Availability Of Infrastructure',
              reportNumber: 'Report 58',
              showState:false
            },
            {
              id: 4,
              name: 'Institution-Wise Number of Students Residing in Hostels - ALL',
              reportNumber: 'Report 111',
              showState:false
            },
            {
              id: 5,
              name: 'Institution-Wise Number of Students Residing in Hostels - University',
              reportNumber: 'Report 111A',
              showState:false
            },
            {
              id: 6,
              name: 'Institution-Wise Number of Students Residing in Hostels - College',
              reportNumber: 'Report 111B',
              showState:false
            },
            {
              id: 7,
              name: 'Institution-Wise Number of Students Residing in Hostels - Standalone Institutions',
              reportNumber: 'Report 111C',
              showState:false
            },
            // {
            //   id: 8,
            //   name: 'Number of Students Residing in Hostels In University & Its Colleges - ALL',
            //   reportNumber: 'Report 112',
            // },
            {
              id: 8,
              name: 'Institution-Wise Availability Of Infrastructure',
              reportNumber: 'Report 131',
              showState:false
            },
            // {
            //   id: 10,
            //   name: 'Institution-Wise Number Of Staff Quarters',
            //   reportNumber: 'Report 139',
            // },
            // {
            //   id: 11,
            //   name: 'Number Of Staff Quarters In University & Its Colleges',
            //   reportNumber: 'Report 140',
            // },


      ],
    },
    {
      title: 'Pupil Teacher Ratio',
      reports: [

            {
              id: 1,
              name: 'State-Wise Pupil Teacher Ratio In Different Types Of Institutions',
              reportNumber: 'Report 64',
              showState:false
            },
            // {
            //   id: 2,
            //   name: 'Institution-Wise Pupil-Teacher Ratio',
            //   reportNumber: 'Report 143',
            // },
            // {
            //   id: 3,
            //   name: 'State-Wise Pupil-Teacher Ratio',
            //   reportNumber: 'Report 144',
            // },

      ],
    },


    {
      title: 'List Of Institutions',
      reports: [

            {
              id: 1,
              name: 'List Of All Colleges',
              reportNumber: 'Report 66',
              showState:false
            },
            {
              id: 2,
              name: 'List Of All Universities',
              reportNumber: 'Report 141',
              showState:false
            },
            {
              id: 3,
              name: 'List of Standalone Institutions',
              reportNumber: 'Report 146',
              showState:false
            },

      ],
    },
    // {
    //   title: 'Data Sharing',
    //   reports: [
    //         {
    //           id: 1,
    //           name: 'Unit Level Data',
    //           reportNumber: 'downloadUnitLevelData',

    //         },
    //   ]
    // }
  ],
}
