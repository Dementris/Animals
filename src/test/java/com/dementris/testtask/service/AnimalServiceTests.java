package com.dementris.testtask.service;

import com.dementris.testtask.exceptions.AnimalNotFoundException;
import com.dementris.testtask.exceptions.IncorrectFileContentTypeException;
import com.dementris.testtask.exceptions.IncorrectFileDataException;
import com.dementris.testtask.model.Animal;
import com.dementris.testtask.model.AnimalRepository;
import com.dementris.testtask.service.dto.AnimalsParamsDto;

import org.bson.types.ObjectId;
import org.hamcrest.MatcherAssert;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;

import org.mockito.MockedStatic;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ByteArrayResource;

import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mockStatic;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AnimalServiceTests {

    @Autowired
    AnimalService service;
    @MockBean
    AnimalRepository repository;

    String hex = "5f0a32c2781d0e3a284a61d6";
    @Test
    public void getAll_IfExists_ReturnsAnimals() {
        // Arrange
        AnimalsParamsDto params = new AnimalsParamsDto("TestType", 4, "TestType", "cost");

        Animal animal = new Animal();
        animal.setName("TestName");
        animal.setType(params.type());
        animal.setSex(params.sex());
        animal.setWeight(255);
        animal.setCost(255);
        animal.setCategory();

        Animal animal1 = new Animal();
        animal1.setName("TestName");
        animal1.setType(params.type());
        animal1.setSex(params.sex());
        animal1.setWeight(255);
        animal1.setCost(255);
        animal1.setCategory();

        List<Animal> mockedAnimals = new ArrayList<>();
        mockedAnimals.add(animal);
        mockedAnimals.add(animal1);
        Mockito.when(repository.get(params.type(), params.category(), params.sex(), "cost")).thenReturn(mockedAnimals);
        // Act
        List<Animal> result = service.get(params);
        // Assert
        MatcherAssert.assertThat(result, hasSize(2));
        MatcherAssert.assertThat(result, contains(animal, animal1));
        MatcherAssert.assertThat(result, everyItem(hasProperty("name", is("TestName"))));
        MatcherAssert.assertThat(result, everyItem(hasProperty("type", is("TestType"))));
        MatcherAssert.assertThat(result, everyItem(hasProperty("sex", is("TestType"))));
        MatcherAssert.assertThat(result, everyItem(hasProperty("weight", is(255))));
        MatcherAssert.assertThat(result, everyItem(hasProperty("cost", is(255))));

    }

    @Test
    public void getAll_IfNotExists_ThrowsException() {
        // Arrange
        AnimalsParamsDto params = new AnimalsParamsDto("TestType", 4, "TestSex", "cost");

        Animal animal = new Animal();
        animal.setName("TestName");
        animal.setType(params.type());
        animal.setSex(params.sex());
        animal.setWeight(255);
        animal.setCost(255);
        animal.setCategory();

        Animal animal1 = new Animal();
        animal1.setName("TestName1");
        animal1.setType(params.type());
        animal1.setSex(params.sex());
        animal1.setWeight(255);
        animal1.setCost(255);
        animal1.setCategory();

        List<Animal> mockedAnimals = new ArrayList<>();
        mockedAnimals.add(animal);
        mockedAnimals.add(animal1);
        Mockito.when(repository.get(params.type(), params.category(), params.sex(), "cost")).thenReturn(mockedAnimals);
        // Act
        Executable executable = () -> service.get(new AnimalsParamsDto("NotExists", 255, "NotExists", "NotExists"));
        // Assert
        Assertions.assertThrows(AnimalNotFoundException.class,
                executable);
    }

    @Test
    public void createFromFile_IfContentTypeTextCsvAndCorrectFileContent_ReturnsAnimals() {

        //Arrange
        ObjectId id = new ObjectId(hex);

        MockedStatic<ObjectId> mocked = mockStatic(ObjectId.class);
        mocked.when(ObjectId::get).thenReturn(id);

        Animal mockedAnimal = new Animal();
        mockedAnimal.setId(String.valueOf(id));
        mockedAnimal.setName("Test");
        mockedAnimal.setType("test");
        mockedAnimal.setSex("test");
        mockedAnimal.setWeight(0);
        mockedAnimal.setCost(0);
        mockedAnimal.setCategory();

        List<Animal> mockedAnimals = new ArrayList<>();
        mockedAnimals.add(mockedAnimal);

        MultipartFile mockedFile = Mockito.mock(MultipartFile.class);
        Mockito.when(mockedFile.getContentType()).thenReturn("text/csv");
        Mockito.when(mockedFile.getResource()).thenReturn(new ByteArrayResource("Name,Type,Sex,Weight,Cost\nTest,test,test,0,0".getBytes()));

        Mockito.when(repository.post(mockedAnimals)).thenReturn(mockedAnimals);
        //Act
        List<Animal> result = service.post(mockedFile);
        mocked.close();
        //Assert
        MatcherAssert.assertThat(result, hasSize(1));
        MatcherAssert.assertThat(result, contains(mockedAnimal));
        MatcherAssert.assertThat(result, everyItem(hasProperty("name", is("Test"))));
        MatcherAssert.assertThat(result, everyItem(hasProperty("type", is("test"))));
        MatcherAssert.assertThat(result, everyItem(hasProperty("sex", is("test"))));
        MatcherAssert.assertThat(result, everyItem(hasProperty("weight", is(0))));
        MatcherAssert.assertThat(result, everyItem(hasProperty("cost", is(0))));

    }

    @Test
    public void createFromFile_IfContentTypeTextCsvAndIncorrectFileContent_ThrowsException() {

        //Arrange
        ObjectId id = new ObjectId("5f0a32c2781d0e3a284a61d7");

        MockedStatic<ObjectId> mocked = mockStatic(ObjectId.class);
        mocked.when(ObjectId::get).thenReturn(id);

        Animal mockedAnimal = new Animal();
        mockedAnimal.setId(String.valueOf(id));
        mockedAnimal.setName("Test");
        mockedAnimal.setType("test");
        mockedAnimal.setSex("test");
        mockedAnimal.setWeight(0);
        mockedAnimal.setCost(0);
        mockedAnimal.setCategory();

        List<Animal> mockedAnimals = new ArrayList<>();
        mockedAnimals.add(mockedAnimal);

        MultipartFile mockedFile = Mockito.mock(MultipartFile.class);
        Mockito.when(mockedFile.getContentType()).thenReturn("text/csv");
        Mockito.when(mockedFile.getResource()).thenReturn(new ByteArrayResource("Name,Type,Sex,Weight,Cost\nTest,test,test,test,test".getBytes()));

        Mockito.when(repository.post(mockedAnimals)).thenReturn(mockedAnimals);
        //Act
        Executable executable = () -> service.post(mockedFile);
        mocked.close();
        //Assert
        Assertions.assertThrows(IncorrectFileDataException.class,
                executable);


    }

    @Test
    public void createFromFile_IfContentTypeTextXmlAndCorrectFileContent_ReturnsAnimals() {

        //Arrange
        ObjectId id = new ObjectId("5f0a32c2781d0e3a284a61d6");

        MockedStatic<ObjectId> mocked = mockStatic(ObjectId.class);
        mocked.when(ObjectId::get).thenReturn(id);

        Animal mockedAnimal = new Animal();
        mockedAnimal.setId(String.valueOf(id));
        mockedAnimal.setName("Test");
        mockedAnimal.setType("test");
        mockedAnimal.setSex("test");
        mockedAnimal.setWeight(0);
        mockedAnimal.setCost(0);
        mockedAnimal.setCategory();

        List<Animal> mockedAnimals = new ArrayList<>();
        mockedAnimals.add(mockedAnimal);

        MultipartFile mockedFile = Mockito.mock(MultipartFile.class);
        Mockito.when(mockedFile.getContentType()).thenReturn("text/xml");
        Mockito.when(mockedFile.getResource()).thenReturn(new ByteArrayResource((
                "<animals>\n" +
                        "\t<animal>\n" +
                        "\t\t<name>Test</name>\n" +
                        "\t\t<type>test</type>\n" +
                        "\t\t<sex>test</sex>\n" +
                        "\t\t<weight>0</weight>\n" +
                        "\t\t<cost>0</cost>\n" +
                        "\t</animal>\n" +
                        "</animals>").getBytes()));

        Mockito.when(repository.post(mockedAnimals)).thenReturn(mockedAnimals);
        //Act
        List<Animal> result = service.post(mockedFile);
        mocked.close();
        //Assert
        MatcherAssert.assertThat(result, hasSize(1));
        MatcherAssert.assertThat(result, contains(mockedAnimal));
        MatcherAssert.assertThat(result, everyItem(hasProperty("name", is("Test"))));
        MatcherAssert.assertThat(result, everyItem(hasProperty("type", is("test"))));
        MatcherAssert.assertThat(result, everyItem(hasProperty("sex", is("test"))));
        MatcherAssert.assertThat(result, everyItem(hasProperty("weight", is(0))));
        MatcherAssert.assertThat(result, everyItem(hasProperty("cost", is(0))));
    }

    @Test
    public void createFromFile_IfContentTypeTextXmlAndIncorrectFileContent_ThrowsException() {

        //Arrange
        ObjectId id = new ObjectId("5f0a35c2781d0e5a284a61d7");

        MockedStatic<ObjectId> mocked = mockStatic(ObjectId.class);
        mocked.when(ObjectId::get).thenReturn(id);

        Animal mockedAnimal = new Animal();
        mockedAnimal.setId(String.valueOf(id));
        mockedAnimal.setName("Test");
        mockedAnimal.setType("test");
        mockedAnimal.setSex("test");
        mockedAnimal.setWeight(0);
        mockedAnimal.setCost(0);
        mockedAnimal.setCategory();

        List<Animal> mockedAnimals = new ArrayList<>();
        mockedAnimals.add(mockedAnimal);

        MultipartFile mockedFile = Mockito.mock(MultipartFile.class);
        Mockito.when(mockedFile.getContentType()).thenReturn("text/xml");
        Mockito.when(mockedFile.getResource()).thenReturn(new ByteArrayResource((
                "<animals>\n" +
                        "\t<animal>\n" +
                        "\t\t<name>Test</name>\n" +
                        "\t\t<type>test</type>\n" +
                        "\t\t<sex>test</sex>\n" +
                        "\t\t<weight>test</weight>\n" +
                        "\t\t<cost>test</cost>\n" +
                        "\t</animal>\n" +
                        "</animals>").getBytes()));
        Mockito.when(repository.post(mockedAnimals)).thenReturn(mockedAnimals);
        //Act
        Executable executable = () -> service.post(mockedFile);
        mocked.close();
        //Assert
        Assertions.assertThrows(IncorrectFileDataException.class,
                executable);
    }

    @Test
    public void createFromFile_IfIncorrectContentType_ThrowsException() {

        //Arrange
        MultipartFile mockedFile = Mockito.mock(MultipartFile.class);
        Mockito.when(mockedFile.getContentType()).thenReturn("text/test");
        //Act
        Executable executable = () -> service.post(mockedFile);
        //Assert
        Assertions.assertThrows(IncorrectFileContentTypeException.class,
                executable);
    }

}

