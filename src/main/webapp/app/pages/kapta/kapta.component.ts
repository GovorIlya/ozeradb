import {Component, OnInit, OnDestroy, NgZone, ElementRef, ViewChild} from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { FormControl } from '@angular/forms';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
 import { Subscription } from 'rxjs/Rx';

 import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
  import { Observable } from 'rxjs/Rx';

import { Kapta } from './kapta.model';
import { KaptaService } from './kapta.service';
import { Principal } from '../../shared';
import {MapsAPILoader} from '@agm/core';
// import {} from '@types/googlemaps';
import { } from 'googlemaps';
// import {google} from '@agm/core/services/google-maps-types';
import {el} from '@angular/platform-browser/testing/src/browser_util';

@Component({
    selector: 'jhi-kapta',
    templateUrl: './kapta.component.html',
    styleUrls: ['kapta.component.css']
})
export class KaptaComponent implements OnInit, OnDestroy {

    lat: number;
    lng: number;
    public zoom: number;
    public searchControl: FormControl;
    @ViewChild('mapElement') mapElement: ElementRef;
    @ViewChild('inputElement') inputElement: ElementRef;
    @ViewChild('search')public searchElement:ElementRef;

    kapta: Kapta = new Kapta();

    currentAccount: any;
    eventSubscriber: Subscription;
    isSaving: Boolean;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;

    constructor(
        private kaptaService: KaptaService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private mapsAPILoader: MapsAPILoader,
        private ngZone: NgZone,
        private principal: Principal
    ) {
    }

    loadAll() {

    }

    ngOnInit() {

        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });



        this.registerChangeInKaptas();

        this.zoom = 4;
        this.lat = 55.776952;
        this.lng = 37.389405;

        //create search FormControl
        this.searchControl = new FormControl();

        this.setCurrentPosition();

       this.mapsAPILoader.load().then(
           ()=>{
               let autocomplete = new google.maps.places.Autocomplete(this.searchElement.nativeElement, {
                   types:['(regions)'],  //"address"
                   componentRestrictions: {country:'by'}
               });

               autocomplete.addListener("place_changed", ()=>{
                   this.ngZone.run(()=>{
                       //get the place result
                       let place: google.maps.places.PlaceResult = autocomplete.getPlace();

                       if(place.geometry === undefined || place.geometry === null){
                           return;
                       }


                       this.lat = place.geometry.location.lat();
                       this.lng = place.geometry.location.lng();
                       this.zoom = 15;
                   });
               });
           }
       )


    }

    ngOnDestroy() {

         this.eventManager.destroy(this.eventSubscriber);
    }
    registerChangeInKaptas() {
        this.eventSubscriber = this.eventManager.subscribe('kaptaListModification', (response) => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }
    private onError(error) {

        this.jhiAlertService.error(error.message, null, null);
    }
    ngAfterViewInit(): void {
        /**
         * Init map api [google.maps]
         */
        /*
        this.gapi.init.then((maps: any) => {
            const loc = new maps.LatLng(55.81800989, 49.09815408);

            this.map = new maps.Map(this.mapElement.nativeElement, {
                zoom: 13,
                center: loc,
                scrollwheel: false,
                panControl: false,
                mapTypeControl: false,
                zoomControl: true,
                streetViewControl: false,
                scaleControl: true,
                zoomControlOptions: {
                    style: maps.ZoomControlStyle.LARGE,
                    position: maps.ControlPosition.RIGHT_BOTTOM
                }
            });

            const input = this.inputElement.nativeElement;
            const options = {
                componentRestrictions: {country: 'ru'}
            };

            const autocomplete = new maps.places.Autocomplete(input, options);

            autocomplete.addListener('place_changed', () => {
                const place = autocomplete.getPlace();
                const location = place.geometry.location;

                this.map.setZoom(13);
                this.map.setCenter({
                    lat: location.lat(),
                    lng: location.lng()
                });
            });
        }); */
    }

    private setCurrentPosition(){
        if("geolocation" in navigator){
            navigator.geolocation.getCurrentPosition((position => {
                this.lat=position.coords.latitude;
                this.lng=position.coords.longitude;
                this.zoom=12;
            }));
        }
    }
}
