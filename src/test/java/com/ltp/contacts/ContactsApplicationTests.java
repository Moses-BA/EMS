// package com.ltp.contacts;


// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;

// import com.ltp.contacts.exception.EntityNotFoundException;
// import com.ltp.contacts.pojo.Contact;
// import com.ltp.contacts.repository.ContactRepository;
// import com.ltp.contacts.service.ContactService;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.mockito.Mockito.doNothing;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.time.LocalDate;
// import java.util.Optional;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;

// @SpringBootTest
// class ContactsApplicationTests {

// 	@MockBean
// 	ContactRepository contactRepository;

//     @Autowired
//     private ContactService contactService;

// 	private Contact[] contacts = new Contact[] {
// 		new Contact("Jon Snow", "6123456432", "agsnejdj7@gmail.com", "4th Lake street, Kubwa", LocalDate.parse(("1980-01-31")), "Manager"),
//         new Contact("Tyrion Lannister", "69876543", "ahshdhe57@gmail.com", "4th Lake street, Gwarinpa", LocalDate.parse(("1985-03-31")), "HR"),
//         new Contact("Carl Johnson", "645673212", "INSIGNE7@gmail.com", "4th Lake street, Wuse", LocalDate.parse(("1987-05-31")), "HOD"),
//         new Contact("Steve Curry", "123456789", "bruhssa7@gmail.com", "4th Lake street, Lugbe", LocalDate.parse(("1989-07-31")), "Janitor"),
// 	};

// 	@SuppressWarnings("null")
//     @BeforeEach
//     void setup(){
// 		for (int i = 0; i < contacts.length; i++) {
// 			contactRepository.save(contacts[i]);
// 		}
//     }

// 	@AfterEach
// 	void clear(){
// 		contactRepository.deleteAll();
//     }

//     @Test
//     public void getContactById_notFound() {
//         // No need to set up repository mock because it'll return empty Optional by default

//         // Execute and assert exception
//         EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
//             contactService.getContactById(10L); // ID that doesn't exist
//         });

//         assertThat(exception.getMessage()).contains("The contact with id '10' does not exist in our records");
//     }

//     @Test
//     public void getContactById_success() {
//         // Set up mock behavior
//         when(contactRepository.findById(1L)).thenReturn(Optional.of(contacts[0]));

//         // Execute the method
//         Contact result = contactService.getContactById(1L);

//         // Assertions
//         assertThat(result).isNotNull();
//         assertThat(result.getName()).isEqualTo("Jon Snow");
//     }

//     @Test
//     public void saveContact_success() {
//         // Prepare test data
//         Contact newContact = new Contact("Stephen Curry", "123456789", "bruhssa7@gmail.com", "4th Lake street, Lugbe", LocalDate.parse(("1989-07-31")), "Janitor");

//         // Mock the behavior to return the saved contact
//         when(contactRepository.save(newContact)).thenReturn(newContact); 

//         // Execute the method
//         contactService.saveContact(newContact);

//         // Verify interaction using Mockito
//         verify(contactRepository, times(1)).save(newContact); 
//     }

//     @Test
//     public void deleteContact_success() {
//         // Prepare test data
//         Long existingContactId = 3L;

//         // Stub repository behavior to simulate typical delete operation
//         doNothing().when(contactRepository).deleteById(existingContactId); 

//         // Execute the method
//         contactService.deleteContact(existingContactId);

//         // Verify with Mockito 
//         verify(contactRepository, times(1)).deleteById(existingContactId); 
//     }

// }



