package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.dto.TestimonialRequest;
import com.alkemy.ong.model.Testimonial;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class TestimonialMapper {

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private Environment enviroment;

	public Testimonial dtoToEntity(TestimonialRequest testimonialRequest) {

		Testimonial testimonial = modelMapper.map(testimonialRequest, Testimonial.class);
		testimonial.setLastUpdated(Date.from(Instant.now()));
		testimonial.setCreationDate(Date.from(Instant.now()));

		return testimonial;
	}

	public void updateEntity(TestimonialRequest testimonialRequest, Testimonial testimonial) {

		testimonial.setName(testimonialRequest.getName());
		updateEntityWithoutName(testimonialRequest, testimonial);

	}

	public void updateEntityWithoutName(TestimonialRequest testimonialRequest, Testimonial testimonial) {

		testimonial.setImage(testimonialRequest.getImage());
		testimonial.setContent(testimonialRequest.getContent());
		testimonial.setLastUpdated(Date.from(Instant.now()));

	}

	private List<TestimonialDto> toTestimonialDtoList(Page<Testimonial> testimonialPage) {

		List<TestimonialDto> testimonialDtos = new ArrayList<>();

		if (testimonialPage.hasContent()) {

			testimonialDtos = testimonialPage.getContent().stream().map(testimonial -> {

				return new TestimonialDto(testimonial.getId(), testimonial.getName(), testimonial.getImage(),
						testimonial.getContent());

			}).collect(Collectors.toList());
		}

		return testimonialDtos;

	}

	public PageDto<TestimonialDto> toPageDto(Page<Testimonial> testimonialPage, Integer pageNumber,
			Integer totalPages) {

		PageDto<TestimonialDto> pageDto = new PageDto<>();
		
		String hostName=InetAddress.getLoopbackAddress().getHostName();
		
		String portNumber=enviroment.getProperty("local.server.port");
		
		pageDto.setTotalPages(totalPages);

		if (testimonialPage.hasNext()) {

			pageDto.setNextPage(hostName+":"+portNumber+"/testimonials?page=" + (pageNumber + 1));
		}

		if (testimonialPage.hasPrevious()) {

			pageDto.setPreviousPage(hostName+":"+portNumber+"/testimonials?page=" + (pageNumber - 1));
		}

		pageDto.setList(toTestimonialDtoList(testimonialPage));

		return pageDto;

	}

}
